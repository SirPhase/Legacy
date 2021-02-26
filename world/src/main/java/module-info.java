module com.legacy.world {
    requires org.apache.logging.log4j;
    requires com.legacy.character;
    requires com.legacy.utils;

    exports com.legacy.world;
    exports com.legacy.world.home;
    exports com.legacy.world.location;
}