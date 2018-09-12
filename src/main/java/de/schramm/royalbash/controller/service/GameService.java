package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.gameevent.GameEvent;
import lombok.Builder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Builder
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game retrieveGame(String gameId) {
        return gameRepository.findOne(gameId);
    }

    public Game createGame(String account1Id, String account2Id) {

        val game = Game.builder()
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

    public Game commitGameEvent(GameEvent gameEvent) {
        return Game.builder().build();
    }
}
