package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class DealDamageToPlayerEffectTest {

    @Test
    fun should_deal_damage_to_target_player() {

        // Given
        val testee = DealDamageToPlayerEffect.builder()
                .amountOfDamage(2)
                .build()
        val player1 = Player.builder().build()
        val player2 = Player.builder()
                .hitpoints(20)
                .build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
        val context = Context(
                game,
                player1,
                targetPlayer = player2
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player2.hitpoints).isEqualTo(18)
    }
}
