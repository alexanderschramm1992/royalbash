package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Game;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class StateResponse {

    private UUID id;
    private UUID accountRed;
    private UUID accountBlue;
    private Board board;

    private String reason;

    public static StateResponse fromGame(Game game) {

        return StateResponse.builder()
                .id(game.getId())
                .accountRed(game.getAccountRed().getId())
                .accountBlue(game.getAccountBlue().getId())
                .build();
    }

    public static StateResponse fromError(String reason) {

        return StateResponse.builder()
                .reason(reason)
                .build();
    }
}
