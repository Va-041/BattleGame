package com.gamebattle.customExceptions;

public class CharacterCreationException extends Throwable {

    public CharacterCreationException(String message) {
        super(message);
    }
    public CharacterCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
