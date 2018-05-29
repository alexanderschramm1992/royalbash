package de.schramm.royalbash.gameengine.exception;

public abstract class GameEngineException extends Exception {

    static final long serialVersionUID = 1L;

    GameEngineException(String message) {

        super(message);
    }
}
