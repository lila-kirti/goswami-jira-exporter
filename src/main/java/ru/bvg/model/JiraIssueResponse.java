package ru.bvg.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssueResponse implements Serializable{
    private List<JiraIssue> issues;
    //количество записей в ответе
    private int maxResults;
    //offset для пейджинга
    private int startAt;
    //общее количество записей
    private int total;
}
