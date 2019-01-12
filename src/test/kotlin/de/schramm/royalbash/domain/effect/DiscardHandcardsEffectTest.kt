package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.card.CardMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DiscardHandcardsEffectTest {

    @Test
    fun should_discard_handcards_for_target_player() {

        // Given
        val testee = DiscardHandcardsEffect(2)
        val player1 = Player("Id 1", handcards = listOf(CardMock("Id 2"), CardMock("Id 3")))
        val player2 = Player("Id 4")
        val game = Game("Id 5", player1 = player1, player2 = player2)
        val context = Context(
                game,
                player2,
                targetPlayer = player1
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.handcards).isEmpty()
    }

    @Test
    fun should_do_nothing_if_target_player_has_no_handcards() {

        // Given
        val testee = DiscardHandcardsEffect(2)
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val game = Game("Id 3", player1 = player1, player2 = player2)
        val context = Context(
                game,
                player2,
                targetPlayer = player1
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.handcards).isEmpty()
    }
}
