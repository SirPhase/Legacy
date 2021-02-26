module com.legacy.character {
    requires com.legacy.utils;
    requires org.apache.logging.log4j;

    exports com.legacy.character;
    exports com.legacy.character.career;
    exports com.legacy.character.body;
    exports com.legacy.character.body.lower;
    exports com.legacy.character.body.lower.genitals;
    exports com.legacy.character.body.upper;
    exports com.legacy.character.body.upper.head;
    exports com.legacy.character.gender;
    exports com.legacy.character.race;
    exports com.legacy.character.player;
    exports com.legacy.character.slave;
    exports com.legacy.character.npc;
}