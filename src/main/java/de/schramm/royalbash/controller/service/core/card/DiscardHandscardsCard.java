package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.CardOnPlayerContext;
import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
class DiscardHandscardsCard implements Card {

    private final String id;
    private final String name;
    private final int amountOfCards;
    private final int cost;

    @Override
    public Game invoke(CardOnPlayerContext cardOnPlayerContext) {

        val game = cardOnPlayerContext.getGame();
        val target = cardOnPlayerContext.getTargetPlayer();

        return game.findPlayer(target)
                .map(player -> player.discardCards(amountOfCards))
                .map(player -> game.updatePlayer(target, player))
                .orElse(game);
    }

    @Override
    public boolean isPlaceableOnSpot() {
        return false;
    }
}
