package de.schramm.royalbash.tdd.core.card;

import de.schramm.royalbash.tdd.core.Card;
import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import de.schramm.royalbash.tdd.core.Player;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DealDamageToPlayerCard implements Card {

    private final String name;
    private final int amountOfDamage;
    private final int cost;

    @Override
    public Game invoke(Context context) {
        return context.getGame()
                .getPlayer(context.getTargetPlayer())
                .map(player -> player.setHitpoints(player.getHitpoints() - amountOfDamage))
                .map(player -> updateTargetPlayer(player, context))
                .orElse(context.getGame());
    }

    @Override
    public boolean canBePlacedOnSpot() {
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
