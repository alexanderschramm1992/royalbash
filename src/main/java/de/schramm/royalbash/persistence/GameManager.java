package de.schramm.royalbash.persistence;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.model.Game;
import de.schramm.royalbash.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GameManager {

    private final GameRepository repository;

    @Autowired
    public GameManager(GameRepository repository) {
        this.repository = repository;
    }

    public Game findGame(UUID gameId) throws DomainObjectDoesNotExistException {

        try{

            Game game = repository.findOne(gameId);
            if (game == null) {

                throw new IllegalArgumentException();
            }
            return game;
        } catch (Exception e) {

            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Game %s not found",
                            gameId
                    )
            );
        }
    }

    public void saveGame(Game game) {

        repository.save(game);
    }
}