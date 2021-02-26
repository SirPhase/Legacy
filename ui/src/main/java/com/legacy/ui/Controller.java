package com.legacy.ui;

import com.legacy.character.CharacterException;
import com.legacy.character.CharacterUtils;
import com.legacy.character.slave.Slave;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.ui.ControllerUtils.*;
import static com.legacy.ui.FXUtils.gameInfo;
import static com.legacy.world.WorldUtils.getHome;

public class Controller extends BorderPane implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();

    @FXML
    public HBox topBox, bottomBox, playerBox;

    @FXML
    public VBox leftBox, centerBox, rightBox;

    @FXML
    public Button homeButton, worldButton, peopleButton;

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.MAIN_FXML_FILE));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        homeButton.setOnAction(actionEvent -> homeScreen());
        worldButton.setOnAction(actionEvent -> worldScreen());
        peopleButton.setOnAction(actionEvent -> peopleScreen());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addCenter(Node node) {
        addNode(centerBox, node);
        gameInfo();
    }

    public void clearCenter() {
        clearNode(centerBox);
        gameInfo();
    }

    public void startScreen() {
        topBox.setVisible(false);
        clearNode(centerBox);
    }

    /**
     * Display the home screen
     */
    public void homeScreen() {
        clearCenter();
        // Base related information
        TextFlow baseText = new TextFlow();
        Text baseName = new Text(getHome().getName() + "\n");
        Text baseDescription = new Text(getHome().generateHomeText());
        VBox slavesBox = new VBox();
        addNode(slavesBox, new Label("Slaves"));
        for (Slave slave : getPlayer().getSlaves()) {
            Text slaveText = new Text(slave.getFullName());
            slaveText.onMouseClickedProperty().setValue(FXUtils.slavePage(slave));
            slaveText.setCursor(Cursor.HAND);
            addNode(slavesBox, slaveText);
        }
        addNode(baseText, baseName, baseDescription, slavesBox);
        addCenter(baseText);
        // Base actions

        // Notes and updates
    }

    /**
     * Display the world screen
     */
    public void worldScreen() {
        clearCenter();
        HBox locationsBox = new HBox();

        // Have a random assortment of slaves and people wandering around

        // Slave Market
//        Button slaveMarketButton = Utils.createButton("Slave Market", e -> {
//           SlaveMarket.slavesScreen();
//        });

        // Normal Market

        // University
        // attend classes, capture university students

        // The Lucky Cat tavern

//        addNode(locationsBox, slaveMarketButton);
        addCenter(locationsBox);
    }

    /**
     * Display the people screen
     */
    public void peopleScreen() {
        clearCenter();
        VBox slavesBox = new VBox(); // Box for list of slaves
        for (Slave slave : CharacterUtils.getPlayer().getSlaves()) {
            Button slaveButton = FXUtils.createButton(slave.getFullName(), e -> {
                clearCenter();
                TextFlow slaveText = new TextFlow(); // Slave text information
                Text name = new Text(slave.getFullName());
                name.setStyle("-fx-font-weight: bold");
                Text meta = new Text(slave.getFormattedMeta() + "\n");
                Text description = new Text(slave.getDescription() + "\n");
                addNode(slaveText, name, meta, description);
                addCenter(slaveText);
                HBox interactionsBox = new HBox(); // Box for slave interactions
                Button talkButton = FXUtils.createButton("Talk", e1 -> {

                });
                addNode(interactionsBox, talkButton);
                if (slave.getAge() >= Integer.parseInt(Utils.getProperties().getProperty(Constants.CONFIG_MINIMUM_AGE))) {
                    Button breedButton = FXUtils.createButton("Breed", actionEvent -> {
                        try {
                            slave.impregnatedBy(CharacterUtils.getPlayer());
                        } catch (CharacterException ex) {
                            LOGGER.error(ex);
                        }
                    });
                    addNode(interactionsBox, breedButton);
                }
                Button backButton;
                backButton = FXUtils.backButton(e1 -> peopleScreen());
                addNode(centerBox, interactionsBox, backButton);
            });
            addNode(slavesBox, slaveButton);
        }
        addCenter(slavesBox);
    }
}
