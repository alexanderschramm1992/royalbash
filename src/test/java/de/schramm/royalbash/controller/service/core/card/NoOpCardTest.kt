package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class NoOpCardTest {

    @Test
    fun should_not_alter_game() {

        // Given
        val testee = NoOpCard.builder().build()
        val player1 = Player.builder()
                .handcard(testee)
                .build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
        val context = Context(game, player1, player2)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isEqualTo(game)
    }

}