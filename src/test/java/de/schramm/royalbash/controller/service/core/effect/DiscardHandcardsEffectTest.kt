package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.card.NoOpCard
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class DiscardHandcardsEffectTest {

    @Test
    fun should_discard_handcards_for_target_player() {

        // Given
        val testee = DiscardHandcardsEffect(2)
        val player1 = Player.builder()
                .handcard(NoOpCard.builder().build())
                .handcard(NoOpCard.builder().build())
                .build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
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
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
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
