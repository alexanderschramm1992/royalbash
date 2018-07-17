package de.schramm.royalbash.core.exception;

public class RuleViolationException extends GameEngineException {

    static final long serialVersionUID = 1L;

    public RuleViolationException(String message) {
        super(message);
    }
}
