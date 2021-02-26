package com.legacy.world.home;

import com.legacy.character.slave.Slave;
import com.legacy.utils.Utils;
import com.legacy.world.location.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

import static com.legacy.character.CharacterUtils.getPlayer;

public class Home extends House {

    private static final Logger LOGGER = LogManager.getLogger();

    private String name;
    private String description;
    private int value;
    private int slaveCapacity;

    public Home(UUID ownerID) {
        super(ownerID);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSlaveCapacity() {
        return slaveCapacity;
    }

    public void setSlaveCapacity(int slaveCapacity) {
        this.slaveCapacity = slaveCapacity;
    }

    public String generateHomeText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.description).append("\n");
        if (!getPlayer().getSlaves().isEmpty()) {
            Slave randomSlave = getPlayer().getSlaves().get(Utils.rand(0, getPlayer().getSlaves().size() - 1));
            stringBuilder.append("You see ").append(randomSlave.getFullName()).append(" wandering around. ");
        }
        return stringBuilder.toString();
    }
}
