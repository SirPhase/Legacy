package com.legacy.character;

public class CharacterException extends Exception {

    public CharacterException(String string) {
        super(string);
    }

    public CharacterException(Exception ex) {
        super(ex);
    }
}
