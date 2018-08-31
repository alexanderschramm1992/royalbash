package de.schramm.royalbash.tdd.core.card;

import de.schramm.royalbash.tdd.core.Card;
import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
class DiscardHandscardsCard implements Card {

    private final int amountOfCards;
    private final int cost;

    @Override
    public Game invoke(Context context) {

        val game = context.getGame();
        val target = context.getTargetPlayer();

        return game.getPlayer(target)
                .map(player -> player.discardCards(amountOfCards))
                .map(player -> game.updatePlayer(target, player))
                .orElse(game);
    }

    @Override
    public boolean canBePlacedOnSpot() {
        return false;
    }
}
