package ru.bvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvg.enumeration.ScriptureEnum;

import java.sql.PreparedStatement;

@Service
class ExporterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    Integer savePlace(String name) {
        Integer id = jdbcTemplate.queryForObject("select id from location where lower(name)=?", Integer.class, name.toLowerCase());
        if (id == null) {
            id = insertRefbook("location", null, capitalizeFirstLetter(name));
        }
        return id;
    }

    private Integer insertRefbook(String table, Integer id, String name) {
        KeyHolder tagsetKeyHolder = new GeneratedKeyHolder();

        if (id == null) {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(String.format("insert into %s (name) values(?) returning id", table),
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

    private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
