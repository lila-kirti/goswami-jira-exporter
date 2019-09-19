package ru.bvg.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollectionTypeEnum {
    BG_SEMINAR("Семинары по Бхагавад-гите"),
    SB_SEMINAR("Семинары по Шримад-Бхагаватам"),
    CC_SEMINAR("Семинары по Шри Чайтанье-чаритамрите"),
    SEMINAR("Семинары"),
    PARIKRAM("Паломничества"),
    DISCIPLE_RETREAT("Ретриты учеников"),
    SADHU_SANGA_RETREAT("Садху-санга"),
    BHAKTI_SANGAM_RETREAT("Бхакти-сангама"),
    HOLY_NAME_RETREAT("Ретриты Святого имени"),
    MASTER_RETREAT("Ретриты для наставников"),
    PRITI_LAKSHANAM("Прити-лакшанам"),
    SHDM_RETREAT("Ретриты Школы джапа-медитации");

    private String text;

    public static CollectionTypeEnum getByText(String text) {
        for (CollectionTypeEnum value : values()) {
            if (value.getText().equals(text)) {
                return value;
            }
        }
        return null;
    }

}
