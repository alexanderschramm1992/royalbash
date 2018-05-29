package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.gameengine.model.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StateResponse {

    private Game game;

    private String reason;

    public static StateResponse fromGame(Game game) {

        return StateResponse.builder()
                .game(game)
                .build();
    }

    public static StateResponse fromError(String reason) {

        return StateResponse.builder()
                .reason(reason)
                .build();
    }
}
