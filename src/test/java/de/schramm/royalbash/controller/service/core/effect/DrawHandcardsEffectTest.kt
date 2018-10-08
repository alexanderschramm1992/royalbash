package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.card.CardMock
import de.schramm.royalbash.controller.service.core.card.NoOpCard
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class DrawHandcardsEffectTest {

    @Test
    fun should_draw_handcards_for_target_player() {

        // Given
        val testee = DrawHandcardsEffect.builder()
                .amountOfCards(2)
                .build()
        val player1 = Player.builder()
                .deckcard(CardMock("Id 1"))
                .deckcard(NoOpCard.builder().build())
                .build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
        val context = Context(game, player2, targetPlayer = player1)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.handcards)
                .hasSize(2)
                .contains(NoOpCard.builder().build())
    }

    @Test
    fun should_do_nothing_if_target_player_has_no_deckcards() {

        // Given
        val testee = DrawHandcardsEffect.builder()
                .amountOfCards(2)
                .build()
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
        val context = Context(
                game,
                player1
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.handcards).isEmpty()
    }
}
