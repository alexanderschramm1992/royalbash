package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.CardMock
import org.assertj.core.api.Assertions
import org.junit.Test

class CardPlayedOnSpotEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnSpotEvent(
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
        Assertions.assertThat(updatedGame)
                .isNotNull
        Assertions.assertThat(updatedGame.player1.getHandcards().count())
                .describedAs("Number of Handcards")
                .isEqualTo(0)
    }
}