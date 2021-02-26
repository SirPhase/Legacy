package com.legacy.ui;

import com.legacy.character.CharacterException;
import com.legacy.character.CharacterUtils;
import com.legacy.character.slave.Slave;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.ui.ControllerUtils.*;

public class FXUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private FXUtils() {}

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

    public static EventHandler<? super MouseEvent> slavePage(Slave slave) {
        return e -> {
            getController().clearCenter();
            TextFlow slaveText = new TextFlow(); // Slave text information
            Text name = new Text(slave.getFullName());
            name.setStyle("-fx-font-weight: bold");
            Text meta = new Text(slave.getFormattedMeta() + "\n");
            Text description = new Text(slave.getDescription() + "\n");
            addNode(slaveText, name, meta, description);
            getController().addCenter(slaveText);
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
//            backButton = FXUtils.backButton(e1 -> peopleScreen());
//            addNode(getController().centerBox, interactionsBox, backButton);
            addNode(getController().centerBox, interactionsBox);
        };
    }
}
