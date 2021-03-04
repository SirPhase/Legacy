package com.legacy.app;

import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.player.Player;
import com.legacy.character.race.Race;
import com.legacy.character.slave.Slave;
import com.legacy.character.slave.SlaveUtils;
import com.legacy.ui.Controller;
import com.legacy.ui.ControllerUtils;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;
import com.legacy.world.home.HomeUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.character.CharacterUtils.setPlayer;
import static com.legacy.ui.ControllerUtils.getController;
import static com.legacy.utils.Utils.getProperty;
import static com.legacy.utils.Utils.loadProperties;

public class App extends Application {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void start(Stage stage) throws Exception {
        Utils.setProperties(loadProperties(Constants.CONFIG_PROPERTIES_FILE));
        ControllerUtils.setController(new Controller());

        stage.setTitle("Legacy v" + getProperty(Constants.CONFIG_VERSION));
        Scene scene = new Scene(getController());
        getController().setMainScene(scene);
        getController().addStyleSheet("stylesheet.css");
        stage.setScene(scene);
        stage.show();

        LOGGER.debug("Application Setup");
        if (Boolean.parseBoolean(getProperty(Constants.CONFIG_DEBUG))) {
            debugStart();
        } else {
            start();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start() {
        getController().startScreen();
    }

    public void debugStart() {
        newGame();

        getPlayer().setFullName("James", "Laske");
        getPlayer().setGender(Gender.MALE);
        getPlayer().setRace(Race.HUMAN);
        BodyUtils.fixCharacter(getPlayer());

        Slave slave = SlaveUtils.generateSlave(getPlayer().getId());
        slave.setAge(Integer.parseInt(getProperty(Constants.CONFIG_MINIMUM_AGE)));

//        getController().homeScreen();
        start();
    }

    public void newGame() {
        setPlayer(new Player());
        HomeUtils.generateHome();
    }
}
