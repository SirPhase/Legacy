package com.legacy.world.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.world.WorldUtils.getHome;
import static com.legacy.world.WorldUtils.setHome;

public class HomeUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private HomeUtils() {}

    public static void generateHome() {
        if (Objects.nonNull(getPlayer())) {
            setHome(new Home(getPlayer().getId()));
            getHome().setName("Home");
            getHome().setSlaveCapacity(10);
            getHome().setDescription("This is your home.");
        } else {
            LOGGER.error("Player is not defined");
        }
    }
}
