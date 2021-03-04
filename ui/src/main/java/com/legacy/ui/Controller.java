package com.legacy.ui;

import com.legacy.ui.scenes.MainPages;
import com.legacy.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.legacy.ui.ControllerUtils.*;

public class Controller extends BorderPane implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();

    @FXML
    public HBox topBox, bottomBox, playerBox;

    @FXML
    public VBox leftBox, centerBox, rightBox;

    private Scene mainScene;

    private Pane previousPage;

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.MAIN_FXML_FILE));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Intentionally left blank
    }

    public Scene getMainScene() {
        return this.mainScene;
    }

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public Pane getPreviousPage() throws NoSuchFieldException {
        if (Objects.nonNull(previousPage)) {
            return previousPage;
        } else {
            throw new NoSuchFieldException();
        }
    }

    public void addCenter(Node node) {
        addNode(centerBox, node);
        gameInfo();
    }

    public void clearCenter() {
        clearNode(centerBox);
        gameInfo();
    }

    public void displayPage(Pane parent) {
        displayPage(parent, false);
    }

    public void displayPage(Pane parent, boolean back) {
        previousPage = new VBox();
        previousPage.getChildren().addAll(centerBox.getChildren());
        clearCenter();
        addCenter(parent);
        if (back) {
            addCenter(ControllerUtils.backButton(previousPage));
        }
    }

    public void displayPage(Pane parent, EventHandler<ActionEvent> backEvent) {
        clearCenter();
        addCenter(parent);
        addCenter(ControllerUtils.backButton(backEvent));
    }

    /**
     * Display the start/menu screen
     */
    public void startScreen() {
        topBox.setVisible(false);
        clearCenter();
        displayPage(MainPages.startPage());
    }

    /**
     * Display the home screen
     */
    public void homeScreen() {
        clearCenter();
        displayPage(MainPages.homePage());
    }

    /**
     * Display the world screen
     */
    public void worldScreen() {
        clearCenter();
        displayPage(MainPages.WorldPage());
    }

    /**
     * Display the people screen
     */
    public void peopleScreen() {
        clearCenter();
    }

    public void addStyleSheet(String styleSheet) {
        if (Objects.nonNull(Controller.class.getClassLoader().getResource(styleSheet))) {
            mainScene.getStylesheets().add(Objects.requireNonNull(Controller.class.getClassLoader().getResource(styleSheet)).toExternalForm());
        }
    }
}
