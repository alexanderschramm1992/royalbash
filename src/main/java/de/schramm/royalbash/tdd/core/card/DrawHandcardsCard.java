package de.schramm.royalbash.tdd.core.card;

import de.schramm.royalbash.tdd.core.Card;
import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DrawHandcardsCard implements Card {

    private final int amountOfCards;

    @Override
    public Game invoke(Context context) {

        val game = context.getGame();
        val targetPlayer = context.getTargetPlayer();

        return game
                .getPlayer(targetPlayer)
                .map(player -> player.drawCards(amountOfCards))
                .map(player -> game.updatePlayer(targetPlayer, player))
                .orElse(game);
    }

    @Override
    public boolean canBePlacedOnSpot() {
        return false;
    }
}
