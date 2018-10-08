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
        val game = Game.builder()
                .player1(Player.builder()
                        .id("Id 2")
                        .spot(Spot.builder()
                                .creature(creature)
                                .build())
                        .build())
                .player2(Player.builder()
                        .hitpoints(5)
                        .build())
                .build()
        val testee = PlayerAttackedEvent.builder()
                .creatureId("Id 1")
                .ownerId("Id 2")
                .build()

        // When
        val updatedGame = testee.invoke(game)

        // Then
        assertThat(updatedGame.player2.hitpoints).isEqualTo(3)
    }
}
