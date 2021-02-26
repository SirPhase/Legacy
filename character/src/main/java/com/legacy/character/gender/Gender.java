package com.legacy.character.gender;

import com.legacy.character.GameCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.legacy.utils.Utils;

public enum Gender {

    // Masculine
    MALE("male", "he", "him", "his", "his", "himself"),

    // Feminine
    FEMALE("female", "she", "her", "her", "hers", "herself"),

    // Neutral
    NONE("none", "it", "it", "its", "N/A", "itself"),

    // Plural Neutral
    THEY("they", "they", "them", "their", "theirs", "themselves");

    private static final Logger LOGGER = LogManager.getLogger();

    private final String name;
    private final String subject;
    private final String object;
    private final String possessiveAdj;
    private final String possessive;
    private final String reflexive;

    Gender(String name, String subject, String object, String possessiveAdj, String possessive, String reflexive) {
        this.name = name;
        this.subject = subject;
        this.object = object;
        this.possessiveAdj = possessiveAdj;
        this.possessive = possessive;
        this.reflexive = reflexive;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.name;
    }

    public String getObject() {
        return this.name;
    }

    public String getPossessiveAdj() {
        return this.name;
    }

    public String getPossessive() {
        return this.name;
    }

    public String getReflexive() {
        return this.name;
    }

    public static Gender determineGender(GameCharacter father, GameCharacter mother) {
        double maleChance = (father.getRace().getMaleOffspringWeight() + mother.getRace().getMaleOffspringWeight())/2;
        double value = Utils.rand(0,1);
        if (value <= maleChance) {
            LOGGER.debug("Gender determined: " + MALE + " (" + (maleChance*100) + "% chance)");
            return MALE;
        } else {
            LOGGER.debug("Gender determined: " + FEMALE + " (" + ((1 - maleChance)*100) + "% chance)");
            return FEMALE;
        }
    }

}
