package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardPlayedOnPlayerEvent implements GameEvent {

    private final String cardId;
    private final String ownerId;
    private final String targetPlayerId;

    @Override
    public Game invoke(Game game) {
        return game.findPlayer(ownerId)
            .map(owner -> owner.findHandcard(cardId)
                    .map(card -> game.findPlayer(targetPlayerId)
                            .map(targetPlayer -> game.playCard(card, owner, targetPlayer))
                            .orElse(game))
                    .orElse(game))
            .orElse(game);
    }

    private Context buildContext(Game game) {
        return Context.builder().build();
    }
}
