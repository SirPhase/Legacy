package com.legacy.character.slave;

import com.legacy.character.CharacterUtils;
import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.race.Race;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.legacy.utils.Constants;

import java.util.Objects;
import java.util.UUID;

public class SlaveUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Slave generateSlave(UUID ownerId) {
        return generateSlave(ownerId, Gender.FEMALE, Race.HUMAN);
    }

    public static Slave generateSlave(UUID ownerId, Gender gender, Race race) {
        Slave slave = new Slave(ownerId);
        slave.setFullName(CharacterUtils.getRandomName(gender), CharacterUtils.getRandomSurname());
        slave.setGender(gender);
        slave.setRace(race);
        slave.setAge(0);
        BodyUtils.fixCharacter(slave);
        slave.setDescription(Constants.EMPTY_STRING);
        slave.setValue(calculateValue(slave));
        LOGGER.debug("Slave generated: " + slave.getId() + " with owner: " + ownerId);
        return slave;
    }

    public static int calculateValue(Slave slave) {
        int value = 0;
        if (Objects.nonNull(slave.getBody().getPenis()) && slave.getBody().getPenis().isVirgin()) {
            LOGGER.trace(slave.getId() + " value + 1000");
            value += 1000;
        }
        if (Objects.nonNull(slave.getBody().getVagina()) && slave.getBody().getVagina().isVirgin()) {
            value += 2000;
            LOGGER.trace(slave.getId() + " value + 2000");
        }
        if (Objects.nonNull(slave.getBody().getAnus()) && slave.getBody().getAnus().isVirgin()) {
            value += 500;
            LOGGER.trace(slave.getId() + " value + 500");
        }
        return value;
    }
}
