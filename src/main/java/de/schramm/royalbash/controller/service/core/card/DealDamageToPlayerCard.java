package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DealDamageToPlayerCard implements Card {

    private final String id;
    private final String name;
    private final int amountOfDamage;
    private final int cost;

    @Override
    public Game invoke(Context context) {
        return context.getTargetPlayer()
                .map(targetPlayer -> context.getGame()
                    .findPlayer(targetPlayer)
                    .map(player -> player.setHitpoints(player.getHitpoints() - amountOfDamage))
                    .map(player -> updateTargetPlayer(player, context))
                    .orElse(context.getGame()))
                .orElse(context.getGame());
    }

    @Override
    public boolean isPlaceableOnSpot() {
        return false;
    }

    private Game updateTargetPlayer(Player updatedPlayer, Context context) {

        val game = context.getGame();

        return context.getTargetPlayer()
                .map(targetPlayer -> game.updatePlayer(targetPlayer, updatedPlayer))
                .orElse(game);
    }
}
