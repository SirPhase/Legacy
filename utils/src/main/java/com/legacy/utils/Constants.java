package com.legacy.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class Constants {

    private Constants() {
        // Hide implicit constructor
    }

    // Files
    public static final String CONFIG_PROPERTIES_FILE = "config.properties";
    public static final String MAIN_FXML_FILE = "main.fxml";
    public static final String MALE_NAMES_FILE = "maleNames.txt";
    public static final String FEMALE_NAMES_FILE = "femaleNames.txt";
    public static final String SURNAMES_FILE = "surnames.txt";

    // Configuration values
    public static final String CONFIG_VERSION = "project.version";
    public static final String CONFIG_DEBUG = "project.debug";
    public static final String CONFIG_MINIMUM_AGE = "game.minimumAge";

    public static final String EMPTY_STRING = "";
    public static final UUID NO_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2150, 1, 1, 0, 0);
}
