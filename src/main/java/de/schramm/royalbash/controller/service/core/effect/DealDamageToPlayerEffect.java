package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DealDamageToPlayerEffect {

    private final int amountOfDamage;

    public Game invoke(Context context) {
        return context.getTargetPlayer()
                .map(targetPlayer -> context.getGame()
                        .findPlayer(targetPlayer)
                        .map(player -> player.setHitpoints(player.getHitpoints() - amountOfDamage))
                        .map(player -> updateTargetPlayer(player, context))
                        .orElse(context.getGame()))
                .orElse(context.getGame());
    }

    private Game updateTargetPlayer(Player updatedPlayer, Context context) {

        val game = context.getGame();

        return context.getTargetPlayer()
                .map(targetPlayer -> game.updatePlayer(targetPlayer, updatedPlayer))
                .orElse(game);
    }
}
