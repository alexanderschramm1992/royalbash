package de.schramm.royalbash.tdd.core.creature;

import de.schramm.royalbash.tdd.core.Card;
import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class NoOpCreature implements Card {

    private final int cost = 0;

    @Override
    public Game invoke(Context context) {

        val game = context.getGame();
        val owner = context.getOwner();
        val targetSpot = context.getTargetSpot();

        val updatedSpot = targetSpot.place(this);

        return game.getPlayer(owner)
                .map(player -> player.updateSpot(targetSpot, updatedSpot))
                .map(player -> game.updatePlayer(owner, player))
                .orElse(game);
    }

    @Override
    public boolean canBePlacedOnSpot() {
        return true;
    }
}
