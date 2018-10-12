package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.CardMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GoblinRaiderTest {

    @Test
    fun should_discard_a_card_of_traget_player_when_placed_on_spot() {

        // Given
        val testee = GoblinRaider(
                "Id 1",
                hitpoints = 1,
                attack = 1,
                cost = 1)
        val spot = Spot()
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3", handcards = listOf(CardMock("Id 4"), CardMock("Id 5")))
        val game = Game("Id 6", player1 = player1, player2 = player2)
        val context = Context(
                game,
                player1,
                targetPlayer = player2,
                targetSpot = spot)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player2.handcards).hasSize(1)
    }

}