package com.legacy.ui.scenes;

import com.legacy.character.gender.Gender;
import com.legacy.character.slave.Slave;
import com.legacy.ui.ControllerUtils;
import com.legacy.utils.Constants;
import com.legacy.utils.Utils;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import static com.legacy.character.CharacterUtils.getPlayer;
import static com.legacy.ui.ControllerUtils.addNode;
import static com.legacy.ui.ControllerUtils.getController;
import static com.legacy.world.WorldUtils.getHome;

public class MainPages {

    private MainPages() {}

    public static VBox homePage() {
        VBox vBox = new VBox();
        TextFlow baseText = new TextFlow();
        Text baseName = ControllerUtils.text(getHome().getName() + "\n");
        Text baseDescription = ControllerUtils.text(getHome().generateHomeText());
        VBox slavesBox = new VBox();
        addNode(slavesBox, new Label("Slaves"));
        for (Slave slave : getPlayer().getSlaves()) {
            Label slaveLabel = new Label(slave.getFullName());
            Tooltip tooltip = new Tooltip(slave.getAge() + " years old, " +
                    slave.getRace().getName() + " " + slave.getGender().getName());
            tooltip.setShowDelay(Duration.millis(250));
            slaveLabel.setTooltip(tooltip);
            ControllerUtils.link(slaveLabel, e ->
                    getController().displayPage(SlavePages.displaySlavePage(slave),
                            event ->getController().displayPage(homePage())));
            if (Gender.MALE.equals(slave.getGender())) {
                slaveLabel.setStyle(slaveLabel.getStyle() + " -fx-text-fill: #03a1fc");
            } else if (Gender.FEMALE.equals(slave.getGender())) {
                slaveLabel.setStyle(slaveLabel.getStyle() + " -fx-text-fill: #f28da0");
            }
            addNode(slavesBox, slaveLabel);
        }
        addNode(baseText, baseName, baseDescription, slavesBox);
        addNode(vBox, baseText);
        // Base actions

        // Notes and updates
        return vBox;
    }

    public static HBox WorldPage() {
        HBox locationsBox = new HBox();
        return locationsBox;
    }

    public static Pane startPage() {
        VBox vBox = new VBox();
        Label titleLabel = new Label("Legacy");
        titleLabel.getStyleClass().addAll("font-size-title", "default-colors");
        Label versionLabel = new Label("Version " + Utils.getProperty(Constants.CONFIG_VERSION));
        versionLabel.getStyleClass().addAll("font-size-subtitle", "default-colors");
        Text text = ControllerUtils.text("This game is a text-based erotic RPG. You must agree to the game's disclaimer " +
                "before playing this game.\n");
        TextFlow textFlow = new TextFlow();
        addNode(textFlow, text);
        addNode(vBox, titleLabel, versionLabel, textFlow);
        return vBox;
    }
}
