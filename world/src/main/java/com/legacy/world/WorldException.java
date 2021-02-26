package com.legacy.world;

public class WorldException extends Exception {

    public WorldException(String exception) {
        super(exception);
    }

    public WorldException(Exception exception) {
        super(exception);
    }
}
