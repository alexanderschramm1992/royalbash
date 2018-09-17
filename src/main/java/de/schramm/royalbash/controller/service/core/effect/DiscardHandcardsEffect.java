package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DiscardHandcardsEffect {

    private final int amountOfCards;

    public Game invoke(Context context) {

        val game = context.getGame();
        val target = context.getTargetPlayer();

        return context.getTargetPlayer()
                .flatMap(targetPlayer -> game.findPlayer(targetPlayer)
                        .map(player -> player.discardCards(amountOfCards))
                        .map(player -> game.updatePlayer(targetPlayer, player)))
                .orElse(game);
    }
}
