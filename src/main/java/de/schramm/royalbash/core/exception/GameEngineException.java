package de.schramm.royalbash.core.exception;

public abstract class GameEngineException extends Exception {

    static final long serialVersionUID = 1L;

    GameEngineException(String message) {

        super(message);
    }
}
