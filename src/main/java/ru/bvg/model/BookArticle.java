package ru.bvg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.bvg.util.JsonDateDeserializer;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BookArticle implements Serializable{
    private String title;
    private String teaser;
    private String text;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date date;
    private String imgUri;

    public BookArticle(String title, String teaser, String text, Date date) {
        this.title = title;
        this.teaser = teaser;
        this.text = text;
        this.date = date;
    }
}
