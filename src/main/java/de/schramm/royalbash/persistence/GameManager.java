package de.schramm.royalbash.persistence;

import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.domain.game.Game;
import de.schramm.royalbash.core.domain.game.board.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Stream;

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

    public Player findEnemyPlayer(UUID playerId, Game game) throws DomainObjectDoesNotExistException {

        return Stream.of(game.getBoard().getPlayerBlue(), game.getBoard().getPlayerRed())
                .filter(player -> !player.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                    String.format(
                            "Cannot find enemy player of %s in constants %s",
                            playerId,
                            game.getId()
                    )
                ));
    }
}
