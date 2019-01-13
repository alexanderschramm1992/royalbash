package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TurnEndedEventTest {

    @Test
    fun should_end_players_turn() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val game = Game(
                "Id 3",
                player1 = player1,
                player2 = player2,
                playerOnTurn = player1
        )
        val testee = TurnEndedEvent("Id 1")

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.playerOnTurn).isEqualTo(player2)
    }
}