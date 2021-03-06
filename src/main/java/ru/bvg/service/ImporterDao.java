package ru.bvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.bvg.model.BookArticle;
import ru.bvg.model.Media;
import ru.bvg.model.Refbook;
import ru.bvg.model.Transcript;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImporterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<BookArticle> getArticles(){
        return jdbcTemplate.query("select id, title, teaser, body, occurrence_date from media where type='article'",
                (resultSet, i) -> new BookArticle(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5)));
    }

    public List<Media> getMediaIdWithType() {
        return namedParameterJdbcTemplate.query(
                "select id, type from media where visible=true order by type",
                Collections.EMPTY_MAP,
                (resultSet, i) -> {
                    Media cd = new Media();
                    cd.setId(resultSet.getInt(1));
                    cd.setType(resultSet.getString(2));
                    return cd;
                });
    }

    @Transactional
    public Integer savePlace(String name) {
        List<Integer> ids = jdbcTemplate.query("select id from location where lower(name)=?", new Object[]{name.toLowerCase()},
                new SingleColumnRowMapper<Integer>());
        if (ids.isEmpty()) {
            return insertRefbook("location", null, capitalizeFirstLetter(name));
        }
        return ids.get(0);
    }

    @Transactional
    public List<Integer> saveLabels(List<String> tags) {
        List<Integer> result = new ArrayList<>();
        List<String> newTags = tags.stream().map(t -> t.replaceAll("_", " ")).collect(Collectors.toList());
        Map<String, Object> params = new HashMap<>();
        params.put("tags", newTags);
        List<Refbook> existingTags = namedParameterJdbcTemplate.query("select id, name from tag where lower(name) in (:tags)",
                params,
                (resultSet, i) -> {
                    return new Refbook(resultSet.getInt(1), resultSet.getString(2));
                });
        result.addAll(existingTags.stream().map(Refbook::getId).collect(Collectors.toList()));
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

    @Transactional
    public void saveMedia(Media media) {
        KeyHolder mediaKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into media (title, teaser, jira_ref, text, occurrence_date, issue_date, category_id, img_url, file_url, location_id, duration, size, language, type) " +
                                    "values (?,?,?,?,?,?,?,?,?,?,?::interval,?,?::lang,?::media_type)",
                            new String[]{"id"});
                    ps.setString(1, media.getTitle());
                    ps.setString(2, media.getTeaser());
                    ps.setString(3, media.getJiraKey());
                    ps.setString(4, media.getText());
                    ps.setDate(5, new java.sql.Date(media.getDate().getTime()));
                    ps.setDate(6, new java.sql.Date(media.getIssueDate().getTime()));
                    if (media.getCategoryId() != null)
                        ps.setInt(7, media.getCategoryId());
                    else
                        ps.setNull(7, Types.INTEGER);
                    ps.setString(8, media.getImgUri());
                    ps.setString(9, media.getFileName());
                    if (media.getPlaceId() != null)
                        ps.setInt(10, media.getPlaceId());
                    else
                        ps.setNull(10, Types.INTEGER);
                    ps.setString(11, media.getDuration());
                    if (media.getSize() != null)
                        ps.setInt(12, media.getSize());
                    else
                        ps.setNull(12, Types.INTEGER);
                    ps.setString(13, media.getLanguage());
                    ps.setString(14, media.getType());
                    return ps;
                },
                mediaKeyHolder);
        Integer mediaId = (Integer) mediaKeyHolder.getKey();
        if (!CollectionUtils.isEmpty(media.getScripture())) {
            for (Media.Scripture scripture : media.getScripture()) {
                jdbcTemplate.update("insert into media_scripture (media_id, scripture_id, canto, chapter, verse) values (?, ?, ?, ?, ?)",
                        mediaId, scripture.getId(), scripture.getCanto(), scripture.getChapter(), scripture.getVerse());
            }
        }
        if (!CollectionUtils.isEmpty(media.getTags())) {
            for (Integer tag : media.getTags()) {
                jdbcTemplate.update("insert into media_tag (media_id, tag_id) values (?, ?)", mediaId, tag);
            }
        }
        if (media.getVideo() != null) {
            jdbcTemplate.update("insert into media_data (media_id, data_type, value) values (?, 'video', ?)", mediaId, media.getVideo());
        }
    }

    public void saveTranscript(Transcript transcript) {
        jdbcTemplate.update("insert into transcript (publish, html) values (?, ?)", transcript.getPublish(), transcript.getHtml());
    }

    public void saveTranscriptJiraRef(Integer id, List<String> jiraKeys) {
        jdbcTemplate.update("update transcript set jira_ref=? where id=?", String.join(",", jiraKeys), id);
    }

    public List<Transcript> getTranscripts() {
        return jdbcTemplate.query("select id, publish, html from transcript", (resultSet, i) -> {
            return new Transcript(resultSet.getInt(1), resultSet.getDate(2), resultSet.getString(3));
        });
    }

    private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
