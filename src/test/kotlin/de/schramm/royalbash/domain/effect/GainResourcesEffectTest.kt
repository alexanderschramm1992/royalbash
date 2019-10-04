package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GainResourcesEffectTest {

    @Test
    fun `should add resources for owner`() {

        // Given
        val testee = GainResourcesEffect(2)
        val player1 = Player(id = "Id 1", resources = 1)
        val game =  Game(id = "Id 2", player1 = player1, player2 = Player("Id 3"))
        val context = Context(game = game, owner = player1)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.resources).isEqualTo(3)
    }
}
