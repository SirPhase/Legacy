package com.legacy.character.slave;

import com.legacy.character.CharacterException;
import com.legacy.character.CharacterUtils;
import com.legacy.character.GameCharacter;
import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.gender.GenderException;
import com.legacy.character.player.Player;
import com.legacy.character.race.Race;
import com.legacy.utils.Constants;

import java.util.Objects;
import java.util.UUID;

public class Slave extends GameCharacter {

    private UUID ownerID;
    private int value;

    public Slave(UUID ownerID) {
        this(ownerID, Constants.EMPTY_STRING, Constants.EMPTY_STRING, Constants.EMPTY_STRING, Gender.NONE, Race.NONE);
    }

    public Slave(UUID ownerID, String name, String surname, String description, Gender gender, Race race) {
        super(name, surname, description, gender, race);
        Objects.requireNonNull(ownerID);
        this.ownerID = ownerID;
        if (CharacterUtils.getCharacter(ownerID) instanceof Player) {
            ((Player) CharacterUtils.getCharacter(ownerID)).addSlave(this);
        }
        this.value = 0;
    }

    @Override
    public boolean isSlave() {
        return true;
    }

    public UUID getOwnerID() {
        return this.ownerID;
    }

    public void setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void haveSexWith(GameCharacter character) throws CharacterException {

    }

    @Override
    public Slave impregnatedBy(GameCharacter father, Gender forcedGender) throws CharacterException {
        Slave slave = impregnatedBy(father);
        slave.setGender(forcedGender);
        BodyUtils.fixCharacter(slave);
        return slave;
    }

    @Override
    public Slave impregnatedBy(GameCharacter father) throws CharacterException {
        if (!father.canImpregnate() || !this.canBeImpregnated()) {
            throw new GenderException("Invalid gender combination; Father: " + father.getGender() +
                    " (Can impregnate: " + father.canImpregnate() + "); Mother: " +
                    this.getGender() + " (Can be impregnated: " + this.canBeImpregnated() + ")");
        }
        LOGGER.trace("Child between " + father.getId() + " and " + this.id);
        Slave child = SlaveUtils.generateSlave(
                this.ownerID,
                Gender.determineGender(father, this),
                (Race.determineRace(father, this)));
        child.setFatherID(father.getId());
        child.setMotherID(this.id);
        child.calculateRaceModifier();
        child.setFullName("Baby", father.getSurname());
        child.setBody(BodyUtils.generateBody(father, this));
        BodyUtils.fixCharacter(child);
        child.setBirthday(Constants.DATE_TIME);
        return child;
    }
}
