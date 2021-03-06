package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.application.gameevent.TurnEndedEventDTO
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.playerOnTurn
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
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
                player2 = player2
        )
        val testee = TurnEndedEventDTO("Id 1")

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.playerOnTurn).isEqualTo(player2)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID,
                "${player1.name} ended the turn, it is now ${player2.name}'s turn"))
    }
}
