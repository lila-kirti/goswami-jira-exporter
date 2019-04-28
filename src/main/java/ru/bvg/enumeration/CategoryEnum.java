package ru.bvg.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
    PARIKRAMA("парикрама", 1),
    CELEBRATION("праздник", 2),
    TREATMENT("обращение", 3),
    PUBLIC("публичная лекция", 4),
    DISCIPLE("встреча с учениками", 5),
    KIRTAN("киртан", 6),
    INITIATION("инициация", 7),
    DISCUSSION("обсуждения", 8);

    private String jiraName;
    private Integer id;

    public static CategoryEnum getByJiraName(String jiraName) {
        for (CategoryEnum scripture : values()) {
            if (scripture.getJiraName().equals(jiraName)) {
                return scripture;
            }
        }
        return null;
    }

}
