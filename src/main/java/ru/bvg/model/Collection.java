package ru.bvg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    private String title;
    private String image;
    private List<String> issues;
    private List<String> types;
}
