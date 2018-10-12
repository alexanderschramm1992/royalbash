package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class PlayerAttackedEventTest {

    @Test
    fun should_invoke_attack_on_player() {

        // Given
        val creature = CreatureMock(
                "Id 1",
                attack = 2
        )
        val game = Game(
                "Id 3",
                player1 = Player("Id 2", spots = listOf(Spot(creature))),
                player2 = Player("Id 4", hitpoints = 5))
        val testee = PlayerAttackedEvent("Id 1", "Id 2")

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame.player2.hitpoints).isEqualTo(3)
    }
}
