package ru.bvg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraField implements Serializable {
    //дата лекции
    @JsonProperty("customfield_10050")
    private Date lectureDate;

    //наименование
    @JsonProperty("summary")
    private String name;

    //"красивое" наименование (если есть, берем его)
    @JsonProperty("customfield_12612")
    private String prettyName;

    //длительность записи
    @JsonProperty("customfield_10064")
    private String duration;

    //размер файла
    @JsonProperty("customfield_10513")
    private Integer size;

    //категория
    @JsonProperty("customfield_10055")
    private Option category;

    //священное писание
    @JsonProperty("customfield_12610")
    private Option scripture;

    //глава/стих
    @JsonProperty("customfield_12611")
    private String scriptureVerse;

    //язык лекции
    @JsonProperty("customfield_10060")
    private Option language;

    //название файла лекции
    @JsonProperty("customfield_10070")
    private String fileName;

    //описание
    @JsonProperty("description")
    private String description;

    //текст
    @JsonProperty("customfield_11110")
    private String text;

    //видео (идентификатор youtube)
    @JsonProperty("customfield_12210")
    private String video;

    //место прочтения лекции
    @JsonProperty("customfield_10062")
    private String place;

    //ключевые слова
    private List<String> labels;

    //авторы
    @JsonProperty("customfield_10412")
    private List<Option> authors;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Option {
        private String value;
    }
}


