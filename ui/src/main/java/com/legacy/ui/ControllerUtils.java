package com.legacy.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ControllerUtils {

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
}
