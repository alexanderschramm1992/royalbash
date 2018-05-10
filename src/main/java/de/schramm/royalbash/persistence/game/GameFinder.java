package de.schramm.royalbash.persistence.game;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GameFinder {

    private final GameRepository repository;

    @Autowired
    public GameFinder(GameRepository repository) {
        this.repository = repository;
    }

    public Game findGame(UUID gameId) throws DomainObjectDoesNotExistException {

        return Optional.of(
                repository.findOne(gameId)
        ).orElseThrow(
                () -> new DomainObjectDoesNotExistException(
                        String.format(
                                "Game %s not found",
                                gameId
                        )
                )
        );
    }
}
