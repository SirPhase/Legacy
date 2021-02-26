package com.legacy.character.player;

import com.legacy.character.CharacterException;
import com.legacy.character.GameCharacter;
import com.legacy.character.gender.Gender;
import com.legacy.character.race.Race;
import com.legacy.character.slave.Slave;
import com.legacy.utils.Constants;

import java.util.*;

public class Player extends GameCharacter {

    private int money;
    private final Map<UUID, Slave> slaveMap;

    public Player() {
        this(Constants.EMPTY_STRING, Constants.EMPTY_STRING, Constants.EMPTY_STRING, Gender.NONE, Race.NONE);
    }

    public Player(String name, String surname, String description, Gender gender, Race race) {
        super(name, surname, description, gender, race);
        this.money = 0;
        this.slaveMap = new LinkedHashMap<>();

        setAge(18);
        LOGGER.trace("Player: " + this.id);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String displayMoney() {
        return "$" + this.money;
    }

    public Slave getSlave(UUID slaveID) {
        return slaveMap.getOrDefault(slaveID, null);
    }

    public List<Slave> getSlaves() {
        return new ArrayList<>(slaveMap.values());
    }

    public void addSlave(Slave slave) {
        if (slaveMap.containsKey(slave.getId())) {
            LOGGER.warn("Slave already present: " + slave.getId());
        } else {
            slaveMap.put(slave.getId(), slave);
        }
    }

    public void removeSlave(UUID slaveID) {
        slaveMap.remove(slaveID);
    }

    @Override
    public void haveSexWith(GameCharacter character) throws CharacterException {
        throw new CharacterException("Reverse GameCharacter order");
    }

    @Override
    public GameCharacter impregnatedBy(GameCharacter father, Gender forcedGender) throws CharacterException {
        return null;
    }

    @Override
    public GameCharacter impregnatedBy(GameCharacter father) throws CharacterException {
        return null;
    }
}
