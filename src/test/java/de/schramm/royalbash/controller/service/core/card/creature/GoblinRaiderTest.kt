package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.NoOpCard
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat


class GoblinRaiderTest {

    @Test
    fun should_discard_a_card_of_traget_player_when_placed_on_spot() {

        // Given
        val testee = GoblinRaider(
                "Id 1",
                hitpoints = 1,
                attack = 1,
                cost = 1
        )
        val spot = Spot.builder().build()
        val player1 = Player.builder()
                .spot(spot)
                .build()
        val player2 = Player.builder()
                .handcard(NoOpCard.builder().build())
                .handcard(NoOpCard.builder().build())
                .build()
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()
        val context = Context(
                game,
                player1,
                targetPlayer = player2,
                targetSpot = spot
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player2.handcards).hasSize(1)
    }

}