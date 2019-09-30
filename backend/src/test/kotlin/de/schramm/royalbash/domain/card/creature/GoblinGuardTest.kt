package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.CardMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class GoblinGuardTest {

    @Test
    fun should_discard_a_card_of_traget_player_when_placed_on_spot() {

        // Given
        val testee = GoblinGuard(
                id = "Id 1",
                instanceId = "InstanceId 1",
                hitpoints = 1,
                attack = 1,
                cost = 1)
        val spot = Spot(id = "spot")
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3", handcards = listOf(CardMock(id = "Id 1", instanceId = "InstanceId 1"),
                                                        CardMock(id = "Id 5", instanceId = "InstanceId 5")))
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
