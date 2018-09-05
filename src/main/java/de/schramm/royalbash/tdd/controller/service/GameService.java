package de.schramm.royalbash.tdd.controller.service;

import de.schramm.royalbash.tdd.controller.service.core.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public Game retrieveGame() {
        return Game.builder().build();
    }
}
