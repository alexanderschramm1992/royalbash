package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.card.CardMock
import de.schramm.royalbash.infrastructure.controller.gameevent.CardPlayedOnPlayerEventDTO
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPlayedOnPlayerEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnPlayerEventDTO(
                cardId = "Id 1",
                ownerId = "Id 2",
                targetPlayerId = "Id 2")
        val card = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val player1 = Player("Id 2", handcards = listOf(card))
        val player2 = Player("Id 4")
        val game = Game(
                "Id 3",
                player1 = player1,
                player2 = player2)

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.handcards).hasSize(0)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID, "${player1.name} has played ${card.name} on ${player2.name}"))
    }
}
