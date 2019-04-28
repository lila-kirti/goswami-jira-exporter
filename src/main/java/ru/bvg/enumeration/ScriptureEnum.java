package ru.bvg.enumeration;

public enum ScriptureEnum {
    BHAGAVAD_GITA("бхагавад-гита", 1, "Бхагавад-гита"),
    SRIMAD_BHAGAVATAM("шримад-бхагаватам", 2, "Шримад-бхагаватам"),
    CAITANYA_CHARITAMRITA("чайтанья-чаритамрита", 3, "Чайтанья-чаритамрита"),
    NECTAR_OF_DEVOTION("нектар преданности", 4, "Нектар преданности"),
    NECTAR_OF_INSTRUCTION("нектар наставлений", 5, "Нектар наставлений"),
    ISOPANISHAD("шри ишопанишад", 6, "Шри Ишопанишад"),
    MADHURYA_KADAMBINI("мадхурья-кадмбини", 7, "Мадхурья-кадмбини"),
    RAMAYANA("рамаяна", 8, "Рамаяна");

    private String jiraName;
    private String siteName;
    private Integer id;

    ScriptureEnum(String jiraName, Integer id, String siteName) {
        this.jiraName = jiraName;
        this.siteName = siteName;
        this.id = id;
    }

    public static ScriptureEnum getByJiraName(String jiraName) {
        for (ScriptureEnum scripture : values()) {
            if (scripture.getJiraName().equals(jiraName)) {
                return scripture;
            }
        }
        return null;
    }

    public String getJiraName() {
        return jiraName;
    }

    public Integer getId() {
        return id;
    }

    public String getSiteName() {
        return siteName;
    }
}
