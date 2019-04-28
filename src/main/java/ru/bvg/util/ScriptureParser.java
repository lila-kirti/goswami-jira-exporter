package ru.bvg.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bvg.enumeration.ScriptureEnum;
import ru.bvg.model.Media;

import java.util.*;

public class ScriptureParser {

    public static List<Media.Scripture> parse(ScriptureEnum scriptureEnum, String verseString) {
        switch (scriptureEnum) {
            case BHAGAVAD_GITA:
                return parseBG(verseString);
            case SRIMAD_BHAGAVATAM:
                return parseSB(verseString);
            case CAITANYA_CHARITAMRITA:
                return parseCC(verseString);
            case ISOPANISHAD:
            case NECTAR_OF_INSTRUCTION:
                return parseWithOnlyVerses(scriptureEnum, verseString);
            case NECTAR_OF_DEVOTION:
                return parseWithOnlyChapter(scriptureEnum, verseString);
            default: return Collections.singletonList(new Media.Scripture(scriptureEnum.getId()));
        }
    }

    private static List<Media.Scripture> parseBG(String verseString) {
        List<Media.Scripture> scriptures = new ArrayList<>();
        String[] parts = verseString.split("\\.");
        //только глава
        if (parts.length == 1) {
            List<Integer> chapters = parseInterval(parts[0]);
            for (Integer chapter : chapters) {
                scriptures.add(new Media.Scripture(ScriptureEnum.BHAGAVAD_GITA.getId(), null, chapter, null));
            }
            return scriptures;
        }
        //глава, стих
        if (parts.length == 2) {
            Integer chapter = Integer.parseInt(parts[0]);
            List<Integer> verses = parseInterval(parts[1]);
            for (Integer verse : verses) {
                scriptures.add(new Media.Scripture(ScriptureEnum.BHAGAVAD_GITA.getId(), null, chapter, verse));
            }
        }
        if (parts.length > 2)
            throw new IllegalStateException("Bhagavad-gita verse format incorrect: " + verseString);
        return scriptures;
    }

    private static List<Media.Scripture> parseSB(String verseString) {
        List<Media.Scripture> scriptures = new ArrayList<>();
        String[] parts = verseString.split("\\.");
        //только песнь
        if (parts.length == 1) {
            List<Integer> cantoes = parseInterval(parts[0]);
            for (Integer canto : cantoes) {
                scriptures.add(new Media.Scripture(ScriptureEnum.SRIMAD_BHAGAVATAM.getId(), canto, null, null));
            }
            return scriptures;
        }
        //песнь, глава
        if (parts.length == 2) {
            Integer canto = Integer.parseInt(parts[0]);
            List<Integer> chapters = parseInterval(parts[1]);
            for (Integer chapter : chapters) {
                scriptures.add(new Media.Scripture(ScriptureEnum.SRIMAD_BHAGAVATAM.getId(), canto, chapter, null));
            }
        }
        //песнь, глава, стих
        if (parts.length == 3) {
            Integer canto = Integer.parseInt(parts[0]);
            Integer chapter = Integer.parseInt(parts[1]);
            List<Integer> verses = parseInterval(parts[2]);
            for (Integer verse : verses) {
                scriptures.add(new Media.Scripture(ScriptureEnum.BHAGAVAD_GITA.getId(), canto, chapter, verse));
            }
        }
        if (parts.length > 3)
            throw new IllegalStateException("Bhagavad-gita verse format incorrect: " + verseString);
        return scriptures;
    }

    private static List<Media.Scripture> parseCC(String verseString) {
        List<Media.Scripture> scriptures = new ArrayList<>();
        String[] cantoParts = verseString.split("\\s+");
        Integer canto = CCEnum.getValueByJiraName(cantoParts[0]);
        if (canto == null)
            throw new IllegalStateException("Caitanya Charitamrita verse format incorrect: " + verseString);
        //только часть
        if (cantoParts.length == 1) {
            scriptures.add(new Media.Scripture(ScriptureEnum.CAITANYA_CHARITAMRITA.getId(), canto, null, null));
            return scriptures;
        }
        else {
            String[] parts = cantoParts[1].split("\\.");
            //часть, глава
            if (parts.length == 1) {
                List<Integer> chapters = parseInterval(parts[0]);
                for (Integer chapter : chapters) {
                    scriptures.add(new Media.Scripture(ScriptureEnum.CAITANYA_CHARITAMRITA.getId(), canto, chapter, null));
                }
            }
            //часть, глава, стих
            if (parts.length == 2) {
                Integer chapter = Integer.parseInt(parts[0]);
                List<Integer> verses = parseInterval(parts[1]);
                for (Integer verse : verses) {
                    scriptures.add(new Media.Scripture(ScriptureEnum.CAITANYA_CHARITAMRITA.getId(), canto, chapter, verse));
                }
            }
            if (parts.length > 2)
                throw new IllegalStateException("Caitanya Charitamrita verse format incorrect: " + verseString);
        }

        return scriptures;
    }

    private static List<Media.Scripture> parseWithOnlyVerses(ScriptureEnum scripture, String verseString) {
        List<Media.Scripture> scriptures = new ArrayList<>();
        List<Integer> verses = parseInterval(verseString);
        for (Integer verse : verses) {
            scriptures.add(new Media.Scripture(scripture.getId(), null, null, verse));
        }
        return scriptures;
    }

    private static List<Media.Scripture> parseWithOnlyChapter(ScriptureEnum scripture, String chapterString) {
        List<Media.Scripture> scriptures = new ArrayList<>();
        List<Integer> chapters = parseInterval(chapterString);
        for (Integer chapter : chapters) {
            scriptures.add(new Media.Scripture(scripture.getId(), null, chapter, null));
        }
        return scriptures;
    }


    private static List<Integer> parseInterval(String string) {
        List<Integer> result = new ArrayList<>();
        String[] parts = string.split("-");
        if (parts.length > 2) {
            //todo
        }
        Integer begin = Integer.parseInt(parts[0]);
        result.add(begin);
        if (parts.length == 2) {
            Integer end = Integer.parseInt(parts[1]);
            for (int i = begin + 1; i <= end; i++) {
                result.add(i);
            }
        }
        return result;

    }

    @Getter
    @AllArgsConstructor
    private enum CCEnum {
        ADI("Ади", 1),
        MADHYA("Мадхья", 2),
        ANTYA("Антья", 3);

        private String jiraName;
        private Integer value;

        public static Integer getValueByJiraName(String jiraName) {
            for (CCEnum ccEnum : values()) {
                if (ccEnum.getJiraName().equals(jiraName)) {
                    return ccEnum.getValue();
                }
            }
            return null;
        }
    }
}
