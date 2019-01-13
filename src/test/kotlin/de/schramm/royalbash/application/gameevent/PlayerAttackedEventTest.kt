package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.junit.jupiter.api.Test

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
                player1 = Player("Id 2", spots = listOf(Spot(id = "spot", creature = creature))),
                player2 = Player("Id 4", hitpoints = 5))
        val testee = PlayerAttackedEvent("Id 1", "Id 2")

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame.player2.hitpoints).isEqualTo(3)
    }
}
