package ru.bvg.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ru.bvg.enumeration.CategoryEnum;
import ru.bvg.enumeration.ScriptureEnum;
import ru.bvg.model.BookArticle;
import ru.bvg.model.JiraField;
import ru.bvg.model.JiraIssue;
import ru.bvg.model.Media;
import ru.bvg.util.ScriptureParser;

import java.util.Collections;
import java.util.Date;


public class MediaMapper {

    private static final Logger logger = LoggerFactory.getLogger(MediaMapper.class);

    public Media map(JiraIssue jiraIssue) {
        JiraField jiraFields = jiraIssue.getFields();
        Media media = new Media(jiraIssue.getKey());
        media.setType("audio");
        media.setTitle(StringUtils.isEmpty(jiraFields.getPrettyName()) ? jiraFields.getName() : jiraFields.getPrettyName());
        media.setTeaser(jiraFields.getDescription());
        media.setText(jiraFields.getText());
        media.setDate(jiraFields.getLectureDate());
        media.setIssueDate(new Date());
        media.setDuration(jiraFields.getDuration());
        media.setSize(jiraFields.getSize());
        media.setImgUri("lecture/lecture_default.jpg");

        //категория
        if (jiraFields.getCategory() != null) {
            CategoryEnum categoryEnum = CategoryEnum.getByJiraName(jiraFields.getCategory().getValue());
            if (categoryEnum != null) {
                media.setCategoryId(categoryEnum.getId());
            }
            media.setImgUri(getImgUrlByCategory(categoryEnum));
        }
        //священные писания
        if (jiraFields.getScripture() != null) {
            ScriptureEnum scriptureEnum = ScriptureEnum.getByJiraName(jiraFields.getScripture().getValue());
            if (scriptureEnum == null) {
                throw new IllegalStateException("Scripture " + jiraFields.getScripture().getValue() + " not found!");
            }
            media.setScripture(Collections.singletonList(new Media.Scripture(scriptureEnum.getId())));
            //глава/стих
            if (!StringUtils.isEmpty(jiraFields.getScriptureVerse())) {
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
            media.setImgUri(getImgUrlByScripture(scriptureEnum));
        }
        //язык
        if (jiraFields.getLanguage() == null || jiraFields.getLanguage().getValue().equals("Русский"))
            media.setLanguage("RUS");
        else if (jiraFields.getLanguage().getValue().equals("English"))
            media.setLanguage("ENG");
        media.setFileName(jiraFields.getFileName());
        media.setVideo(jiraFields.getVideo());
        return media;
    }

    public Media mapBook(BookArticle bookArticle) {
        return mapBookArticle(bookArticle, "book");
    }

    public Media mapArticle(BookArticle bookArticle) {
        return mapBookArticle(bookArticle, "article");
    }

    private Media mapBookArticle(BookArticle bookArticle, String type) {
        Media media = new Media();
        media.setType(type);
        media.setTitle(bookArticle.getTitle());
        media.setText(bookArticle.getText());
        media.setTeaser(bookArticle.getTeaser());
        media.setDate(bookArticle.getDate());
        media.setIssueDate(new Date());
        media.setImgUri(bookArticle.getImgUri());
        media.setLanguage("RUS");
        return media;
    }

    private String getImgUrlByScripture(ScriptureEnum scriptureEnum){
        switch (scriptureEnum){
            case BHAGAVAD_GITA:
                return "lecture/bg_default.jpg";
            case SRIMAD_BHAGAVATAM:
                return "lecture/sb_default.jpg";
            case CAITANYA_CHARITAMRITA:
                return "lecture/cc_default.jpg";
            case ISOPANISHAD:
                return "lecture/si_default.jpg";
            case NECTAR_OF_INSTRUCTION:
                return "lecture/nn_default.jpg";
            case NECTAR_OF_DEVOTION:
                return "lecture/np_default.jpg";
            case MADHURYA_KADAMBINI:
                return "lecture/kadambini_default.jpg";
            case RAMAYANA:
                return "lecture/ramayana_default.jpg";
            default:
                return "lecture/lecture_default.jpg";
        }
    }

    private String getImgUrlByCategory(CategoryEnum categoryEnum){
        if (categoryEnum == null){
            return "lecture/lecture_default.jpg";
        }
        switch (categoryEnum){
            case CELEBRATION:
                return "lecture/celebration_default.jpg";
            case DISCIPLE:
                return "lecture/darshan_default.jpg";
            case DISCUSSION:
                return "lecture/discussion_default.jpg";
            case INITIATION:
                return "lecture/initiation_default.jpg";
            case KIRTAN:
                return "lecture/kirtan_default.jpg";
            case PARIKRAMA:
                return "lecture/parikram_default.jpg";
            case PUBLIC:
                return "lecture/public_default.jpg";
            case TREATMENT:
                return "lecture/treatment_default.jpg";
            default:
                return "lecture/lecture_default.jpg";
        }

    }
}
