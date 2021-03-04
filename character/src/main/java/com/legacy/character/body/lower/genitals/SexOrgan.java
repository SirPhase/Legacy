package com.legacy.character.body.lower.genitals;

import com.legacy.character.body.BodyColor;
import com.legacy.character.body.BodyPart;
import com.legacy.character.body.BodySize;
import com.legacy.character.race.Race;

public class SexOrgan extends BodyPart {

    private boolean virgin;
    private long sexCount;

    protected SexOrgan() {
        super();
    }

    protected SexOrgan(Race race, BodySize bodySize, BodyColor bodyColor) {
        super(race, bodySize, bodyColor);
        this.virgin = true;
    }

    public boolean isVirgin() {
        return virgin;
    }

    public void setVirgin(boolean virgin) {
        this.virgin = virgin;
    }
}
