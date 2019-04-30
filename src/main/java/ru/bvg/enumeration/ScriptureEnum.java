package ru.bvg.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScriptureEnum {
    BHAGAVAD_GITA("бхагавад-гита", 1),
    SRIMAD_BHAGAVATAM("шримад-бхагаватам", 2),
    CAITANYA_CHARITAMRITA("чайтанья-чаритамрита", 3),
    NECTAR_OF_DEVOTION("нектар преданности", 4),
    NECTAR_OF_INSTRUCTION("нектар наставлений", 5),
    ISOPANISHAD("шри ишопанишад", 6),
    MADHURYA_KADAMBINI("мадхурья-кадамбини", 7),
    RAMAYANA("рамаяна", 8);

    private String jiraName;
    private Integer id;

    public static ScriptureEnum getByJiraName(String jiraName) {
        for (ScriptureEnum scripture : values()) {
            if (scripture.getJiraName().equals(jiraName)) {
                return scripture;
            }
        }
        return null;
    }
}
