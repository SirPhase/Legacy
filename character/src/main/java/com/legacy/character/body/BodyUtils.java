package com.legacy.character.body;

import com.legacy.character.CharacterException;
import com.legacy.character.GameCharacter;
import com.legacy.character.body.lower.Leg;
import com.legacy.character.body.lower.Tail;
import com.legacy.character.body.lower.genitals.*;
import com.legacy.character.body.upper.Arm;
import com.legacy.character.body.upper.Chest;
import com.legacy.character.body.upper.Wing;
import com.legacy.character.body.upper.head.*;
import com.legacy.character.gender.Gender;
import com.legacy.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class BodyUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private BodyUtils() {
        // Static Class
    }

    public static Body generateBody() {
        Arm arm = new Arm();
        Butt butt = new Butt();
        Chest chest = new Chest();
        Ear ear = new Ear();
        Eye eye = new Eye();
        Face face = new Face();
        Hair hair = new Hair();
        Leg leg = new Leg();
        Skin skin = new Skin();
        Body.BodyBuilder builder = new Body.BodyBuilder(arm, butt, chest, ear, eye, face, hair, leg, skin);
        builder.appendAnus(new Anus());
        return builder.build();
    }

    public static Body generateBody(GameCharacter father, GameCharacter mother) throws CharacterException {
        if (Objects.isNull(father.getBody()) || Objects.isNull(mother.getBody())) {
            throw new CharacterException("Parent body does not exist");
        }

        Arm arm = (Arm) determineBodyPartFromParents(new Arm(), father.getBody().getArm(), mother.getBody().getArm());
        Butt butt = (Butt) determineBodyPartFromParents(new Butt(), father.getBody().getButt(), mother.getBody().getButt());
        Chest chest = (Chest) determineBodyPartFromParents(new Chest(), father.getBody().getChest(), mother.getBody().getChest());
        Ear ear = (Ear) determineBodyPartFromParents(new Ear(), father.getBody().getEar(), mother.getBody().getEar());
        Eye eye = (Eye) determineBodyPartFromParents(new Eye(), father.getBody().getEye(), mother.getBody().getEye());
        Face face = (Face) determineBodyPartFromParents(new Face(), father.getBody().getFace(), mother.getBody().getFace());
        Hair hair = (Hair) determineBodyPartFromParents(new Hair(), father.getBody().getHair(), mother.getBody().getHair());
        Leg leg = (Leg) determineBodyPartFromParents(new Leg(), father.getBody().getLeg(), mother.getBody().getLeg());
        Skin skin = (Skin) determineBodyPartFromParents(new Skin(), father.getBody().getSkin(), mother.getBody().getSkin());
        Body.BodyBuilder builder = new Body.BodyBuilder(arm, butt, chest, ear, eye, face, hair, leg, skin);

        try {
            builder.appendAnus((Anus) determineBodyPartFromParents(new Anus(), father.getBody().getAnus(), mother.getBody().getAnus()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; Anus");
        }
        try {
            builder.appendHorn((Horn) determineBodyPartFromParents(new Horn(), father.getBody().getHorn(), mother.getBody().getHorn()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; Horn");
        }
        try {
            builder.appendTail((Tail) determineBodyPartFromParents(new Tail(), father.getBody().getTail(), mother.getBody().getTail()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; Tail");
        }
        try {
            builder.appendWing((Wing) determineBodyPartFromParents(new Wing(), father.getBody().getWing(), mother.getBody().getWing()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; Wing");
        }
        try {
            builder.appendBodyHair((BodyHair) determineBodyPartFromParents(new BodyHair(), father.getBody().getBodyHair(), mother.getBody().getBodyHair()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; BodyHair");
        }
        try {
            builder.appendPubicHair((PubicHair) determineBodyPartFromParents(new PubicHair(), father.getBody().getPubicHair(), mother.getBody().getPubicHair()));
        } catch (CharacterException ex) {
            LOGGER.warn(ex + "; PubicHair");
        }


        return builder.build();
    }

    public static BodyPart determineBodyPartFromParents(BodyPart bodyPart, BodyPart father, BodyPart mother) throws CharacterException {
        if (Objects.isNull(bodyPart) || Objects.isNull(father) || Objects.isNull(mother)) {
            throw new CharacterException("BodyPart does not exist");
        }

        double value = Utils.rand(0,1);
        if (value > 0.5) {
            bodyPart.setRace(father.getRace());
        } else {
            bodyPart.setRace(mother.getRace());
        }
        bodyPart.setBodySize(BodySize.determineBodySize(father, mother));
        return bodyPart;
    }

    public static void fixCharacter(GameCharacter character) {
        if (Objects.isNull(character.getBody())) {
            character.setBody(generateBody());
        }
        // Gender fixes
        try {
            removeGenitals(character);
        } catch (CharacterException ex) {
            LOGGER.error(ex);
        }
        character.getBody().setAnus(new Anus());
        if (Gender.MALE.equals(character.getGender())) {
            character.getBody().setPenis(new Penis());
            character.getBody().setTesticle(new Testicle());
        } else if (Gender.FEMALE.equals(character.getGender())) {
            character.getBody().setVagina(new Vagina());
            character.getBody().setOvary(new Ovary());
        }
    }

    public static void removeGenitals(GameCharacter character) throws CharacterException {
        if (Objects.isNull(character.getBody())) {
            throw new CharacterException("Invalid Body");
        }

        character.getBody().setPenis(null);
        character.getBody().setTesticle(null);
        character.getBody().setVagina(null);
        character.getBody().setOvary(null);
    }
}
