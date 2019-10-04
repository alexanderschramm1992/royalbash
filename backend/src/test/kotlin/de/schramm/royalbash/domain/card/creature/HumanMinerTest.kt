package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HumanMinerTest {

    @Test
    fun `should add three resources to owner when placed on spot`() {
        // Given
        val testee = HumanMiner(
                id = "Id 1",
                instanceId = "InstanceId 1",
                hitpoints = 1,
                attack = 1,
                cost = 1)
        val spot = Spot(id = "Id 2")
        val player1 = Player(id = "Id 3", spots = listOf(spot), resources = 2)
        val player2 = Player(id = "Id 4")
        val game = Game("Id 5", player1 = player1, player2 = player2)
        val context = Context(
                game,
                player1,
                targetSpot = spot)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame.player1.resources).isEqualTo(4)
    }
}
