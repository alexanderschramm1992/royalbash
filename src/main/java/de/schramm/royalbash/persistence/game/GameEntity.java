package de.schramm.royalbash.persistence.game;

import de.schramm.royalbash.model.Game;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class GameEntity {

    private UUID id;
    private UUID accountRed;
    private UUID accountBlue;
    private UUID board;

    public static GameEntity toEntity(Game game) {

        return GameEntity.builder()
                .id(game.getId())
                .accountBlue(game.getAccountBlue().getId())
                .accountRed(game.getAccountRed().getId())
                .board(game.getBoard().getId())
                .build();
    }
}
