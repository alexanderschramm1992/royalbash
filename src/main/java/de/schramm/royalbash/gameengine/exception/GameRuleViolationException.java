package de.schramm.royalbash.gameengine.exception;

public class GameRuleViolationException extends GameEngineException {

    static final long serialVersionUID = 1L;

    public GameRuleViolationException(String message) {

        super(message);
    }
}
