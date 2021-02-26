package com.legacy.character.body;

public enum BodySize {

    PETITE(),
    SMALL(),
    AVERAGE(),
    THICK(),
    CHUBBY(),
    HUGE();

    BodySize() {

    }

    public static BodySize determineBodySize(BodyPart father, BodyPart mother) {
        return BodySize.values()[(father.getBodySize().ordinal() + mother.getBodySize().ordinal())/2];
    }
}
