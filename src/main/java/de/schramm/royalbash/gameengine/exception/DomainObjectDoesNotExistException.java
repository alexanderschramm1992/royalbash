package de.schramm.royalbash.gameengine.exception;

public class DomainObjectDoesNotExistException extends GameEngineException {

    static final long serialVersionUID = 1L;

    public DomainObjectDoesNotExistException(String message) {

        super(message);
    }
}
