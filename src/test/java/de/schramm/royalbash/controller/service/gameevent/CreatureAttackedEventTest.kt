package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class CreatureAttackedEventTest {


    @Test
    @Throws(Exception::class)
    fun should_invoke_attack_on_creature() {

        // Given
        val testee = CreatureAttackedEvent.builder()
                .attackerId("Id 1")
                .ownerId("Id 2")
                .defenderId("Id 3")
                .build()
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 3,
                attack = 2
        )
        val defender = CreatureMock(
                "Id 3",
                hitpoints = 3,
                attack = 1
        )
        val game = Game.builder()
                .player1(Player.builder()
                        .id("Id 2")
                        .spot(Spot.builder()
                                .creature(attacker)
                                .build())
                        .build())
                .player2(Player.builder()
                        .spot(Spot.builder()
                                .creature(defender)
                                .build())
                        .build())
                .build()

        // When
        val updatedGame = testee.invoke(game)

        // Then
        val updatedAttacker = updatedGame.findCreature("Id 1")
                .orElseThrow { Exception("Attacker not present") }
        assertThat(updatedAttacker.hitpoints).isEqualTo(2)
        val updatedDefender = updatedGame.findCreature("Id 3")
                .orElseThrow { Exception("Defender not present") }
        assertThat(updatedDefender.hitpoints).isEqualTo(1)
    }
}