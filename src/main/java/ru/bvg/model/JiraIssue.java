package ru.bvg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue implements Serializable {
    //идентификатор лекции в JIRA
    private String key;
    //поля с данными
    private JiraField fields;
}


