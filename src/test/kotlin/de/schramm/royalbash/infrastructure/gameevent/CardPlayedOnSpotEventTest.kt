package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.CardMock
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardPlayedOnSpotEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnSpotEventDTO(
                cardId = "Id 1",
                ownerId = "Id 2",
                targetSpotId = "Id 3")
        val card = CardMock("Id 1")
        val spot = Spot("Id 3")
        val game = Game(
                "Id 4",
                player1 = Player("Id 2", handcards = listOf(card), spots = listOf(spot)),
                player2 = Player("Id 5"))

        // When
        val updatedGame = testee.invoke(game)

        // Then
        Assertions.assertThat(updatedGame).isNotNull
        Assertions.assertThat(updatedGame.player1.handcards).hasSize(0)
    }
}