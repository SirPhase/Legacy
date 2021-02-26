package com.legacy.character.npc;

import com.legacy.character.CharacterUtils;
import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.race.Race;
import com.legacy.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NPCUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private NPCUtils() {}

    public static NPC generateNPC() {
        return generateNPC(Gender.FEMALE, Race.HUMAN);
    }

    public static NPC generateNPC(Gender gender, Race race) {
        NPC npc = new NPC();
        npc.setFullName(CharacterUtils.getRandomName(gender), CharacterUtils.getRandomSurname());
        npc.setGender(gender);
        npc.setRace(race);
        npc.setAge(0);
        BodyUtils.fixCharacter(npc);
        npc.setDescription(Constants.EMPTY_STRING);
        LOGGER.debug("NPC generated: " + npc.getId());
        return npc;
    }
}
