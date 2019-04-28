package ru.bvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvg.model.Media;
import ru.bvg.model.Refbook;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class ExporterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    Integer savePlace(String name) {
        List<Integer> ids = jdbcTemplate.query("select id from location where lower(name)=?", new Object[]{name.toLowerCase()},
                new SingleColumnRowMapper<Integer>());
        if (ids.isEmpty()) {
            return insertRefbook("location", null, capitalizeFirstLetter(name));
        }
        return null;
    }

    @Transactional
    List<Integer> saveLabels(List<String> tags) {
        List<Integer> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("tags", tags);
        List<Refbook> existingTags = namedParameterJdbcTemplate.query("select id, name from tag where lower(name) in (:tags)",
                params,
                (resultSet, i) -> {
                    return new Refbook(resultSet.getInt(1), resultSet.getString(2));
                });
        result.addAll(existingTags.stream().map(Refbook::getId).collect(Collectors.toList()));
        List<String> newTags = new ArrayList<>(tags);
        newTags.removeAll(existingTags.stream().map(Refbook::getName).collect(Collectors.toList()));
        for (String newTag : newTags) {
            result.add(insertRefbook("tag", null, newTag));
        }
        return result;
    }

    @Transactional
    private Integer insertRefbook(String table, Integer id, String name) {
        KeyHolder tagsetKeyHolder = new GeneratedKeyHolder();

        if (id == null) {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(String.format("insert into %s (name) values(?)", table),
                                new String[]{"id"});
                        ps.setString(1, name);
                        return ps;
                    },
                    tagsetKeyHolder);
        } else {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(String.format("insert into %s (id, name) values(?,?) returning id", table),
                                new String[]{"id"});
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        return ps;
                    },
                    tagsetKeyHolder);
        }
        return tagsetKeyHolder.getKey().intValue();
    }

    public void saveMedia(List<Media> media){

    }

    private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
