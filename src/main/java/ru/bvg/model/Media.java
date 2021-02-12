package ru.bvg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Media  implements Serializable {
    private Integer id;
    private String jiraKey;
    private String type;
    private String title;
    private String teaser;
    private String text;
    private Date date;
    private Date issueDate;
    private String duration;
    private Integer size;
    private Integer categoryId;
    private String imgUri;
    private List<Scripture> scripture;
    private String language;
    private Integer placeId;
    private String fileName;
    private List<Integer> tags;
    private String video;

    public Media(String jiraKey) {
        this.jiraKey = jiraKey;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Scripture{
        private Integer id;
        private Integer canto;
        private Integer chapter;
        private Integer verse;

        public Scripture(Integer id) {
            this.id = id;
        }
    }
}
