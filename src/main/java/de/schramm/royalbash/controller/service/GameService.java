package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
