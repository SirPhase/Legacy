package com.legacy.character.npc;

import com.legacy.character.CharacterException;
import com.legacy.character.GameCharacter;
import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.gender.GenderException;
import com.legacy.character.race.Race;
import com.legacy.character.slave.Slave;
import com.legacy.utils.Constants;

import java.util.UUID;

public class NPC extends GameCharacter {

    public NPC() {
        this(Constants.EMPTY_STRING, Constants.EMPTY_STRING, Constants.EMPTY_STRING, Gender.NONE, Race.NONE);
    }

    public NPC(String name, String surname, String description, Gender gender, Race race) {
        super(name, surname, description, gender, race);
    }

    @Override
    public void haveSexWith(GameCharacter character) throws CharacterException {

    }

    @Override
    public NPC impregnatedBy(GameCharacter father, Gender forcedGender) throws CharacterException {
        NPC npc = impregnatedBy(father);
        npc.setGender(forcedGender);
        BodyUtils.fixCharacter(npc);
        return npc;
    }

    @Override
    public NPC impregnatedBy(GameCharacter father) throws CharacterException {
        if (!father.canImpregnate() || !this.canBeImpregnated()) {
            throw new GenderException("Invalid gender combination; Father: " + father.getGender() +
                    " (Can impregnate: " + father.canImpregnate() + "); Mother: " +
                    this.getGender() + " (Can be impregnated: " + this.canBeImpregnated() + ")");
        }
        LOGGER.trace("Child between " + father.getId() + " and " + this.id);

        NPC child = NPCUtils.generateNPC(
                Gender.determineGender(father, this),
                Race.determineRace(father, this));
        return child;
    }

    public Slave enslave(UUID ownerID) {
        Slave slave = new Slave(ownerID, this.name, this.surname, this.description, this.gender, this.race);
        slave.setId(this.id);
        slave.setBirthday(this.birthday);
        slave.setBody(this.body);
        slave.setRace(this.race);
        slave.setRaceModifier(this.raceModifier);
        slave.setAge(this.age);
        slave.setFatherID(this.fatherID);
        slave.setMotherID(this.motherID);
        slave.getChildrenIDList().addAll(this.getChildrenIDList());
        return slave;
    }
}
