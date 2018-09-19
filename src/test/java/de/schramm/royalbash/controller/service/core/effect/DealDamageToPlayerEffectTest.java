package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealDamageToPlayerEffectTest {

    @Test
    public void should_deal_damage_to_target_player() {

        // Given
        val testee = DealDamageToPlayerEffect.builder()
                .amountOfDamage(2)
                .build();
        val player1 = Player.builder().build();
        val player2 = Player.builder()
                .hitpoints(20)
                .build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player2)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer2().getHitpoints()).isEqualTo(18);
    }
}
