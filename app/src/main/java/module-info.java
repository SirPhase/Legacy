module com.legacy.app {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.logging.log4j;
    requires com.legacy.ui;
    requires com.legacy.utils;
    requires com.legacy.world;
    requires com.legacy.character;

    opens com.legacy.app to javafx.fxml;

    exports com.legacy.app;
}