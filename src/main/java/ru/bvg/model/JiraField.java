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
    @JsonProperty("customfield_10050")
    private Date lectureDate;

    @JsonProperty("summary")
    private String name;

    @JsonProperty("customfield_12612")
    private String prettyName;

    @JsonProperty("customfield_10064")
    private String duration;

    @JsonProperty("customfield_10513")
    private Integer size;

    @JsonProperty("customfield_10055")
    private Option category;

    @JsonProperty("customfield_12610")
    private Option scripture;

    @JsonProperty("customfield_12611")
    private String scriptureVerse;

    @JsonProperty("customfield_10060")
    private Option language;

    @JsonProperty("customfield_10070")
    private String fileName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("customfield_11110")
    private String text;

    @JsonProperty("customfield_12210")
    private String video;

    @JsonProperty("customfield_10062")
    private String place;

    private List<String> labels;

    @JsonProperty("customfield_10412")
    private List<Option> authors;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Option {
        private String value;
    }
}


