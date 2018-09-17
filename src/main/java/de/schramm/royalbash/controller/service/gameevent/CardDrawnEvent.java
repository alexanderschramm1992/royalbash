package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardDrawnEvent implements GameEvent {

    private final String playerId;
    private final int amountOfCards;

    @Override
    public Game invoke(Game game) {
        return game.findPlayer(playerId)
                .map(player -> game.updatePlayer(player, player.drawCards(amountOfCards)))
                .orElse(game);
    }
}
