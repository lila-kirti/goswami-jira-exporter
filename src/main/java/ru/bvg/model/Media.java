package ru.bvg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Media {
    private String jiraKey;
    private String type;
    private String title;
    private String teaser;
    private String text;
    private Date lectureDate;
    private Date issueDate;
    private String duration;
    private Integer size;
    private Integer categoryId;
    private String imgUrl;
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
