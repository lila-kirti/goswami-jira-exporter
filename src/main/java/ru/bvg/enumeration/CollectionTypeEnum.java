package ru.bvg.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollectionTypeEnum {
    SEMINAR("семинар"),
    PARIKRAM("парикрама"),
    DECIPLE_RETREAT("ретрит учеников"),
    DECIPLE_SADHU_SANGA("cадху-санга");

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
