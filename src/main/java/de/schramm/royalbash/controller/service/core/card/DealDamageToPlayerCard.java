package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Card;
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
        return context.getGame()
                .findPlayer(context.getTargetPlayer())
                .map(player -> player.setHitpoints(player.getHitpoints() - amountOfDamage))
                .map(player -> updateTargetPlayer(player, context))
                .orElse(context.getGame());
    }

    @Override
    public boolean isPlaceableOnSpot() {
        return false;
    }

    private Game updateTargetPlayer(Player updatedPlayer, Context context) {

        val game = context.getGame();
        val player1 = game.getPlayer1();
        val player2 = game.getPlayer2();
        val targetPlayer = context.getTargetPlayer();

        return game.toBuilder()
            .player1(targetPlayer.equals(player1) ? updatedPlayer : player1)
            .player2(targetPlayer.equals(player2) ? updatedPlayer : player2)
            .build();
    }
}
