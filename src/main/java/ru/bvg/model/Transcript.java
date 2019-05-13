package ru.bvg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Transcript {
    private Integer id;
    private Date publish;
    private String html;

    public Transcript(Date date, String html) {
        this.publish = date;
        this.html = html;
    }
}
