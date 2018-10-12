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
        val testee = CreatureAttackedEvent(
                attackerId = "Id 1",
                ownerId = "Id 2",
                defenderId = "Id 3")
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 3,
                attack = 2)
        val defender = CreatureMock(
                "Id 3",
                hitpoints = 3,
                attack = 1)
        val game = Game(
                "Id 4",
                player1 = Player("Id 2", spots = listOf(Spot(attacker))),
                player2 = Player("Id 5", spots = listOf(Spot(defender))))

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