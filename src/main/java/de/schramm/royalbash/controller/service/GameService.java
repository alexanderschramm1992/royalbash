package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.gameevent.GameEvent;
import lombok.Builder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Builder
@Service
public class GameService {

    private final UUIDGenerator uuidGenerator;
    private final GameRepository gameRepository;

    @Autowired
    public GameService(final UUIDGenerator uuidGenerator, GameRepository gameRepository) {
        this.uuidGenerator = uuidGenerator;
        this.gameRepository = gameRepository;
    }

    public Optional<Game> retrieveGame(String gameId) {

        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .filter(game -> gameId.equals(game.getId()))
                .findFirst();
    }

    public Game createGame(String account1Id, String account2Id) {

        val game = Game.builder()
                .id(uuidGenerator.generateUUID().toString())
                .player1(Player.builder()
                        .name(account1Id)
                        .build())
                .player2(Player.builder()
                        .name(account2Id)
                        .build())
                .build();

        gameRepository.save(game);

        return game;
    }

    public Game commitGameEvent(String gameId, GameEvent gameEvent) {
        return gameEvent.invoke(gameRepository.findOne(gameId));
    }
}
