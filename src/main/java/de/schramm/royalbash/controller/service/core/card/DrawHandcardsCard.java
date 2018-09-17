package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.CardOnPlayerContext;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DrawHandcardsCard implements Card {

    private final String id;
    private final String name;
    private final int amountOfCards;
    private final int cost;

    @Override
    public Game invoke(CardOnPlayerContext cardOnPlayerContext) {

        val game = cardOnPlayerContext.getGame();
        val targetPlayer = cardOnPlayerContext.getTargetPlayer();

        return game
                .findPlayer(targetPlayer)
                .map(player -> player.drawCards(amountOfCards))
                .map(player -> game.updatePlayer(targetPlayer, player))
                .orElse(game);
    }

    @Override
    public boolean isPlaceableOnSpot() {
        return false;
    }
}
