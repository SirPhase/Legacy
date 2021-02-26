package com.legacy.world;

import com.legacy.character.GameCharacter;
import com.legacy.world.home.Home;
import com.legacy.world.location.House;

public class WorldUtils {

    private static Home home;

    private WorldUtils() {}

    public static Home getHome() {
        return home;
    }

    public static void setHome(Home home) {
        WorldUtils.home = home;
    }

    public static House generateHouse(GameCharacter character) {
        House house = new House(character.getId());
        return house;
    }
}
