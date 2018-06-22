package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.gameengine.model.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StateResponseGame {

    private Game game;

    private String reason;

    public static StateResponseGame fromGame(Game game) {

        return StateResponseGame.builder()
                .game(game)
                .build();
    }

    public static StateResponseGame fromError(String reason) {

        return StateResponseGame.builder()
                .reason(reason)
                .build();
    }
}
