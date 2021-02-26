package com.legacy.character.body;

import com.legacy.character.race.Race;

public abstract class BodyPart {

    protected Race race;
    protected BodySize bodySize;
    protected BodyColor bodyColor;

    protected BodyPart() {
        this(Race.NONE, BodySize.AVERAGE, BodyColor.WHITE);
    }

    protected BodyPart(Race race, BodySize bodySize, BodyColor bodyColor) {
        this.race = race;
        this.bodySize = bodySize;
        this.bodyColor = bodyColor;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public BodySize getBodySize() {
        return bodySize;
    }

    public void setBodySize(BodySize bodySize) {
        this.bodySize = bodySize;
    }

    public BodyColor getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(BodyColor bodyColor) {
        this.bodyColor = bodyColor;
    }
}
