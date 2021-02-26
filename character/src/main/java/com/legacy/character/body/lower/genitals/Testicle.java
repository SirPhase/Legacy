package com.legacy.character.body.lower.genitals;

import com.legacy.character.body.BodyPart;

public class Testicle extends BodyPart {

    private int currentCum;
    private int maxCum;
    private int cumAmount;

    public void ejaculate() {
        currentCum = Math.max(0, currentCum - cumAmount);
    }
}
