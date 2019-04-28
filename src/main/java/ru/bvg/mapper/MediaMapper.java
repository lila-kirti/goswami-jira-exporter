package ru.bvg.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ru.bvg.enumeration.CategoryEnum;
import ru.bvg.enumeration.ScriptureEnum;
import ru.bvg.model.JiraField;
import ru.bvg.model.JiraIssue;
import ru.bvg.model.Media;
import ru.bvg.util.ScriptureParser;

import java.io.Console;
import java.util.Collections;


public class MediaMapper {

    private static final Logger logger = LoggerFactory.getLogger(MediaMapper.class);

    public Media map(JiraIssue jiraIssue) {
        JiraField jiraFields = jiraIssue.getFields();
        Media media = new Media(jiraIssue.getKey());
        media.setLectureDate(jiraFields.getLectureDate());
        media.setTitle(StringUtils.isEmpty(jiraFields.getPrettyName()) ? jiraFields.getName() : jiraFields.getPrettyName());
        media.setDuration(jiraFields.getDuration());
        media.setSize(jiraFields.getSize());

        //категория
        if (jiraFields.getCategory() != null) {
            CategoryEnum categoryEnum = CategoryEnum.getByJiraName(jiraFields.getCategory().getValue());
            if (categoryEnum == null) {
                throw new IllegalStateException("Category " + jiraFields.getCategory().getValue() + " not found!");
            }
            media.setCategoryId(categoryEnum.getId());
        }
        //священные писания
        if (jiraFields.getScripture() != null) {
            ScriptureEnum scriptureEnum = ScriptureEnum.getByJiraName(jiraFields.getScripture().getValue());
            if (scriptureEnum == null) {
                throw new IllegalStateException("Scripture " + jiraFields.getScripture().getValue() + " not found!");
            }
            media.setScripture(Collections.singletonList(new Media.Scripture(scriptureEnum.getId())));
            //глава/стих
            if (StringUtils.isEmpty(jiraFields.getScriptureVerse())) {
                try {
                    media.setScripture(ScriptureParser.parse(scriptureEnum, jiraFields.getScriptureVerse()));
                } catch (RuntimeException e) {
                    System.out.println(String.format("Issue %s has wrong %s verse format: %s",
                           jiraIssue.getKey(), scriptureEnum.getJiraName(), jiraFields.getScriptureVerse()));
                    logger.error("Issue {} has wrong {} verse format: {}",
                            jiraIssue.getKey(), scriptureEnum.getJiraName(), jiraFields.getScriptureVerse());
//                    throw new IllegalStateException(String.format("Issue %s has wrong %s verse format: %s",
//                            jiraIssue.getKey(), scriptureEnum.getJiraName(), jiraFields.getScriptureVerse()));
                }
            }
        }
        //язык
        if (jiraFields.getLanguage() != null) {
            if (jiraFields.getLanguage().getValue().equals("Русский"))
                media.setLanguage("RUS");
            else if (jiraFields.getLanguage().getValue().equals("English"))
                media.setLanguage("ENG");
        }
        media.setTeaser(jiraFields.getDescription());
        media.setVideo(jiraFields.getVideo());
        return media;
    }
}
