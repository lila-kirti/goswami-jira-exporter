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
    private int maxResults;
    private int startAt;
    private int total;
}
