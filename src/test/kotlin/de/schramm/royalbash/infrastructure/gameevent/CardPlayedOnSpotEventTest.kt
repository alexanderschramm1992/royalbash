package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.CardMock
import de.schramm.royalbash.application.gameevent.CardPlayedOnSpotEventDTO
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CardPlayedOnSpotEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnSpotEventDTO(
                cardId = "Id 1",
                ownerId = "Id 2",
                targetSpotId = "Id 3")
        val card = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot("Id 3")
        val player1 = Player("Id 2", handcards = listOf(card), spots = listOf(spot))
        val game = Game(
                "Id 4",
                player1 = player1,
                player2 = Player("Id 5"))

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.handcards).hasSize(0)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID, "${player1.name} has played ${card.name} on a spot"))
    }
}
