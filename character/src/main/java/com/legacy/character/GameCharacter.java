package com.legacy.character;

import com.legacy.character.body.Body;
import com.legacy.character.body.BodyUtils;
import com.legacy.character.career.Career;
import com.legacy.character.gender.Gender;
import com.legacy.character.race.Race;
import com.legacy.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class GameCharacter {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected UUID id;
    protected String name;
    protected String surname;
    protected String description;

    protected LocalDateTime birthday;

    protected Body body;
    protected Gender gender;
    protected Race race;
    protected double raceModifier;
    protected int age;

    protected UUID fatherID;
    protected UUID motherID;
    protected List<UUID> childrenIDList;

    protected List<TraitsEnum> traitsList;
    protected HungerEnum hunger;
    protected MoodEnum mood;

    // Ranges from -100 to 100
    protected int submission;
    protected int corruption;

    protected Career career;

    protected GameCharacter() {
        this(Constants.EMPTY_STRING, Constants.EMPTY_STRING, Constants.EMPTY_STRING, Gender.NONE, Race.NONE);
    }

    protected GameCharacter(
            String name,
            String surname,
            String description,
            Gender gender,
            Race race) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.gender = gender;
        this.race = race;
        this.body = BodyUtils.generateBody();

        this.fatherID = Constants.NO_ID;
        this.motherID = Constants.NO_ID;

        CharacterUtils.addCharacter(this);
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isSlave() {
        return false;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    public void setFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public double getRaceModifier() {
        return this.raceModifier;
    }

    public void setRaceModifier(double raceModifier) {
        this.raceModifier = raceModifier;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getFatherID() {
        return this.fatherID;
    }

    public void setFatherID(UUID fatherID) {
        this.fatherID = fatherID;
    }

    public UUID getMotherID() {
        return this.motherID;
    }

    public void setMotherID(UUID motherID) {
        this.motherID = motherID;
    }

    public List<UUID> getChildrenIDList() {
        if (Objects.isNull(this.childrenIDList)) {
            this.childrenIDList = new ArrayList<>();
        }
        return this.childrenIDList;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public List<TraitsEnum> getTraitsList() {
        if (Objects.isNull(this.traitsList)) {
            this.traitsList = new ArrayList<>();
        }
        return this.traitsList;
    }

    public HungerEnum getHunger() {
        return hunger;
    }

    public void setHunger(HungerEnum hunger) {
        this.hunger = hunger;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public void setMood(MoodEnum mood) {
        this.mood = mood;
    }


    public String getFormattedMeta() {
        return " | " + this.getAge() + " years old | " + this.getRace() + " | " + this.getGender();
    }

    public void calculateRaceModifier() throws CharacterException {
        GameCharacter father = CharacterUtils.getCharacter(this.fatherID);
        GameCharacter mother = CharacterUtils.getCharacter(this.motherID);
        if (Objects.nonNull(father) && Objects.nonNull(mother)) {
            if (Objects.nonNull(this.race) && Objects.nonNull(father.getRace()) && Objects.nonNull(mother.getRace())) {
                if (father.getRace().equals(mother.getRace())) {
                    this.raceModifier = father.getRaceModifier() + mother.getRaceModifier();
                }
                else if (this.race.equals(father.getRace())) {
                    this.raceModifier = father.getRaceModifier();
                } else {
                    this.raceModifier = mother.getRaceModifier();
                }
                this.raceModifier += 0.1;
                LOGGER.trace("Race Modifier for " + this.id + ": " + this.raceModifier);
            } else {
                throw new CharacterException("Race(s) are not defined");
            }
        } else {
            throw new CharacterException("Parent(s) are not defined");
        }
    }

    /*
    Impregnation
     */
    public boolean canImpregnate() {
        return Objects.nonNull(body) && Objects.nonNull(body.getPenis()) && Objects.nonNull(body.getTesticle());
    }
    public boolean canBeImpregnated() {
        return Objects.nonNull(body) && Objects.nonNull(body.getVagina()) && Objects.nonNull(body.getOvary());
    }

    public abstract void haveSexWith(GameCharacter character) throws CharacterException;
    public abstract GameCharacter impregnatedBy(GameCharacter father, Gender forcedGender) throws CharacterException;
    public abstract GameCharacter impregnatedBy(GameCharacter father) throws CharacterException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCharacter)) return false;
        GameCharacter that = (GameCharacter) o;
        return Double.compare(that.getRaceModifier(), getRaceModifier()) == 0 &&
                getAge() == that.getAge() &&
                getId().equals(that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getBirthday(), that.getBirthday()) &&
                Objects.equals(getBody(), that.getBody()) &&
                getGender() == that.getGender() &&
                getRace() == that.getRace() &&
                Objects.equals(getFatherID(), that.getFatherID()) &&
                Objects.equals(getMotherID(), that.getMotherID()) &&
                Objects.equals(getChildrenIDList(), that.getChildrenIDList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getSurname(),
                getDescription(),
                getBirthday(),
                getBody(),
                getGender(),
                getRace(),
                getRaceModifier(),
                getAge(),
                getFatherID(),
                getMotherID(),
                getChildrenIDList());
    }
}
