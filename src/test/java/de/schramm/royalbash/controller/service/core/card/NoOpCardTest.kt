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
        val testee = NoOpCard("Id 1")
        val player1 = Player("Id 2", handcards = listOf(testee))
        val player2 = Player("Id 3")
        val game = Game("Id 4", player1 = player1, player2 = player2)
        val context = Context(game, player1, player2)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isEqualTo(game)
    }

}