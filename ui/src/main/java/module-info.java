module com.legacy.ui {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.logging.log4j;
    requires com.legacy.utils;
    requires com.legacy.character;
    requires com.legacy.world;

    exports com.legacy.ui;
}