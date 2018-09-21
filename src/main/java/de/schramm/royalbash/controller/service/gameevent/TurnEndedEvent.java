package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TurnEndedEvent implements GameEvent {

    private final String playerId;

    @Override
    public Game invoke(Game game) {
        return game.findPlayer(playerId)
                .filter(player -> game.getPlayerOnTurn().equals(player))
                .map(player -> game.toBuilder()
                    .playerOnTurn(player.equals(game.getPlayer1())
                            ? game.getPlayer2()
                            : game.getPlayer1())
                    .build())
                .orElse(game);
    }
}
