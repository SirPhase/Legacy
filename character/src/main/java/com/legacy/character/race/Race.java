package com.legacy.character.race;

import com.legacy.character.GameCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.legacy.utils.Utils;

public enum Race {

    NONE("none", 0.5, 0.5, 0),
    HUMAN("human", 0.5, 0.5, 0.5),

    // Fantasy Races
    ANGEL("angel", 0.25, 0.2, 0.25),
    DEMON("demon", 0.25, 0.2, 0.75),
    DWARF("dwarf", 0.5, 0.4, 0.4),
    ELF("elf", 0.5, 0.4, 0.2),
    GOBLIN("goblin", 0.4, 0.75, 0.8),
    GNOME("gnome", 0.4, 0.5, 0.5),
    HALF_ELF("half elf", 0.5, 0.45, 0.35),
    HALF_ORC("half orc", 0.625, 0.4, 0.35),
    ORC("orc", 0.75, 0.3, 0.2),
    FAIRY("fairy", 0.05, 0.1, 0.8),
    DRAGON("dragon", 0.5, 0.15, 0.05),

    // Beast Races
    CANINE("canine", 0.6, 0.5, 0.5),
    KITSUNE("kitsune", 0.4, 0.4, 0.5),
    RABBIT("rabbit", 0.4, 0.95, 0.95),
    BOVINE("bovine", 0.5, 0.5, 0.5),
    LIZARD("lizardfolk", 0.6, 0.4, 0.5),
    NEKO("neko", 0.4, 0.6, 0.5);

    // Misc Races

    private final String name;
    private final double maleOffspringWeight;
    private final double raceWeight;
    private final double fertility;

    Race(String name, double maleOffspringWeight, double raceWeight, double fertility) {
        this.name = name;
        this.maleOffspringWeight = maleOffspringWeight;
        this.raceWeight = raceWeight;
        this.fertility = fertility;
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public String getName() {
        return this.name;
    }

    public double getMaleOffspringWeight() {
        return this.maleOffspringWeight;
    }

    public double getRaceWeight() {
        return this.raceWeight;
    }

    public double getFertility() {
        return this.fertility;
    }

    public static Race determineRace(GameCharacter father, GameCharacter mother) {
        // Same Race
        if (father.getRace().equals(mother.getRace())) {
            LOGGER.debug("Race determined: " + father.getRace() + " (100.00% chance)");
            return father.getRace();
        }

        // Special Cases
        if (father.getRace().equals(HUMAN)) {
            if (mother.getRace().equals(ELF)) {
                return HALF_ELF;
            } else if (mother.getRace().equals(ORC)) {
                return HALF_ORC;
            }
        } else if (mother.getRace().equals(HUMAN)) {
            if (father.getRace().equals(ELF)) {
                return HALF_ELF;
            } else if (father.getRace().equals(ORC)) {
                return HALF_ORC;
            }
        }

        // Normal Cases
        double fatherChance = father.getRace().getRaceWeight() + father.getRaceModifier();
        LOGGER.trace("Father race weight: " + fatherChance);
        double motherChance = mother.getRace().getRaceWeight() + mother.getRaceModifier();
        LOGGER.trace("Mother race weight: " + motherChance);
        double totalChance = fatherChance + motherChance;
        double fatherRaceChance = fatherChance / totalChance;
        double value = Utils.rand(0,1);
        if (value <= fatherRaceChance) {
            LOGGER.debug("Race determined: " + father.getRace() + " (" + (fatherRaceChance*100) + "% chance)");
            return father.getRace();
        } else {
            LOGGER.debug("Race determined: " + mother.getRace() + " (" + ((1 - fatherRaceChance)*100) + "% chance)");
            return mother.getRace();
        }
    }
}
