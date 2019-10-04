package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealDamageToPlayerEffectTest {

    @Test
    fun should_deal_damage_to_target_player() {

        // Given
        val testee = DealDamageToPlayerEffect(2)
        val player1 = Player("Id 1")
        val player2 = Player("Id 2", hitpoints = 20)
        val game = Game("Id 3", player1 = player1, player2 = player2)
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
