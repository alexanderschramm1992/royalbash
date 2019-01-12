package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.card.CardMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CardDrawnEventTest {

    @Test
    fun should_invoke_draw_card() {

        // Given
        val card = CardMock("Id 1")
        val player1 = Player("Id 2", deckcards = listOf(card))
        val game = Game(
                "Id 3",
                player1 = player1,
                player2 = Player("Id 4"))
        val testee = CardDrawnEvent("Id 2", 1)

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.handcards).hasSize(1)
    }
}
