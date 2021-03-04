package com.legacy.ui;

import com.legacy.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.legacy.character.CharacterUtils.getPlayer;

public class ControllerUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static Controller controller;

    private ControllerUtils() {
        // Private constructor to hide implicit
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller c) {
        controller = c;
    }

    public static void addNode(Pane parent, Node... children) {
        parent.getChildren().addAll(children);
    }

    public static void clearNode(Pane pane) {
        pane.getChildren().clear();
    }

    public static Button createButton(String text, EventHandler<ActionEvent> e) {
        Button button = new Button(text);
        button.setOnAction(e);
        return  button;
    }

    public static Button backButton(EventHandler<ActionEvent> e) {
        Button button = new Button("Back");
        button.setOnAction(e);
        return button;
    }

    public static Button backButton(Pane parent) {
        return backButton(e -> getController().displayPage(parent));
    }

    public static Text link(Text text, EventHandler<MouseEvent> event) {
        text.onMouseClickedProperty().setValue(event);
        text.setId("link-text");
        return text;
    }

    public static Label link(Label label, EventHandler<MouseEvent> event) {
        label.onMouseClickedProperty().setValue(event);
        label.getStyleClass().add("link-label");
        return label;
    }

    public static void gameInfo() {
        clearNode(getController().playerBox);
        if (Objects.nonNull(getPlayer())) {
            Label nameLabel = new Label(getPlayer().getFullName() + " - " +
                    Utils.capitalize(getPlayer().getRace().getName() + " " +
                            Utils.capitalize(getPlayer().getGender().getName())));
            Label infoLabel = new Label("" + getPlayer().getAge() + " Years Old ");
            Label moneyLabel = new Label(getPlayer().displayMoney());
            Label dateLabel = new Label(Utils.getFormattedDate());
            VBox vBox = new VBox();
            addNode(vBox, nameLabel, infoLabel, moneyLabel, dateLabel);
            addNode(getController().playerBox, vBox);
        } else {
            LOGGER.warn("Player object is not defined");
        }
    }

    public static Text text(String string) {
        Text text = new Text(string);
        text.getStyleClass().add("default-text");
        text.setFill(Color.rgb(209, 209, 209));
        text.applyCss();
        return text;
    }
}
