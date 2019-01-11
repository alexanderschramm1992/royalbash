package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.card.CardMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CardPlayedOnPlayerEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnPlayerEvent(
                cardId = "Id 1",
                ownerId = "Id 2",
                targetPlayerId = "Id 2")
        val card = CardMock("Id 1")
        val game = Game(
                "Id 3",
                player1 = Player("Id 2", handcards = listOf(card)),
                player2 = Player("Id 4"))

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.getHandcards().count()).isEqualTo(0)
    }
}
