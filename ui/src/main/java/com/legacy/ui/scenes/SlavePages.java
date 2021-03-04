package com.legacy.ui.scenes;

import com.legacy.character.CharacterException;
import com.legacy.character.CharacterUtils;
import com.legacy.character.slave.Slave;
import com.legacy.ui.ControllerUtils;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.ui.ControllerUtils.addNode;
import static com.legacy.ui.ControllerUtils.getController;

public class SlavePages {

    private static final Logger LOGGER = LogManager.getLogger();

    private SlavePages() {}

    public static VBox displaySlavePage(Slave slave) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Label slaveName = new Label(slave.getFullName());
        StringBuilder sb = new StringBuilder();
        sb.append(slave.getName()).append(" is your slave. ").append(Utils.capitalize(slave.getGender().getSubject()))
                .append(" is a ").append(slave.getGender().getName()).append(" ").append(slave.getRace().getName())
                .append(", and is ").append(slave.getAge()).append(" years old. ");
        Text slaveDescription = ControllerUtils.text(sb.toString());
        addNode(vBox, slaveName, slaveDescription);

        // Interactions
        HBox interactionsBox = new HBox();
        Button talkButton = ControllerUtils.createButton("Talk", e -> {

        });
        Button killButton = ControllerUtils.createButton("Kill", e -> {
            CharacterUtils.removeCharacter(slave);
            getController().displayPage(MainPages.homePage());
        });
        addNode(interactionsBox, talkButton, killButton);
        if (slave.getAge() >= Integer.parseInt(Utils.getProperties().getProperty(Constants.CONFIG_MINIMUM_AGE))) {
            Button breedButton = ControllerUtils.createButton("Breed", actionEvent -> {
                try {
                    slave.impregnatedBy(getPlayer());
                } catch (CharacterException ex) {
                    LOGGER.error(ex);
                }
            });
            addNode(interactionsBox, breedButton);
        }
        addNode(vBox, interactionsBox);
        return vBox;
    }
}
