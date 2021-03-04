package com.legacy.character;

import com.legacy.character.gender.Gender;
import com.legacy.character.player.Player;
import com.legacy.character.slave.Slave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static com.legacy.utils.Utils.rand;

public class CharacterUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final List<String> maleNames = readNamesFile(Constants.MALE_NAMES_FILE);
    private static final List<String> femaleNames = readNamesFile(Constants.FEMALE_NAMES_FILE);
    private static  final List<String> surnames = readNamesFile(Constants.SURNAMES_FILE);

    private static final Map<UUID, GameCharacter> characterMap = new LinkedHashMap();
    private static Player player;

    private CharacterUtils() {}

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player p) {
        player = p;
    }

    public static void addCharacter(GameCharacter gameCharacter) {
        if (characterMap.containsKey(gameCharacter.getId())) {
            LOGGER.warn("Character already present: " + gameCharacter.getId());
        } else {
            characterMap.put(gameCharacter.getId(), gameCharacter);
        }
    }

    public static GameCharacter getCharacter(UUID id) {
        return characterMap.getOrDefault(id, null);
    }

    public static void removeCharacter(GameCharacter gameCharacter) {
        characterMap.remove(gameCharacter.getId());
        if (gameCharacter.isSlave() && Slave.class.isAssignableFrom(gameCharacter.getClass())) {
            if (player.getId().equals(((Slave) gameCharacter).getOwnerID())) {
                player.removeSlave(gameCharacter.getId());
            }
        }
    }

    public static List<String> readNamesFile(String fileName) {
        List<String> names = new ArrayList<>();
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while (Objects.nonNull(line = reader.readLine())) {
                names.add(line);
            }
        } catch (IOException ex) {
            LOGGER.error("Failed to read from file '" + fileName + "': " + ex.getMessage());
        }
        return names;
    }

    public static String getRandomName(Gender gender) {
        if (maleNames.isEmpty()) {
            maleNames.add("");
        }
        if (femaleNames.isEmpty()) {
            femaleNames.add("");
        }
        if (surnames.isEmpty()) {
            surnames.add("");
        }

        if (Gender.MALE.equals(gender)) {
            return maleNames.get(rand(0, maleNames.size()));
        } else if (Gender.FEMALE.equals(gender)) {
            return femaleNames.get(rand(0, femaleNames.size()));
        } else {
            LOGGER.warn("Received invalid gender '" + gender + "', returning random name");
            if (rand(0,1) == 0) {
                return maleNames.get(rand(0, maleNames.size()));
            } else {
                return femaleNames.get(rand(0, femaleNames.size()));
            }
        }
    }

    public static String getRandomSurname() {
        return surnames.get(rand(0, surnames.size()));
    }
}
