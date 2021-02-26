package com.legacy.character.gender;

import com.legacy.character.CharacterException;

public class GenderException extends CharacterException {

    public GenderException(String string) {
        super(string);
    }

    public GenderException(Exception ex) {
        super(ex);
    }
}
