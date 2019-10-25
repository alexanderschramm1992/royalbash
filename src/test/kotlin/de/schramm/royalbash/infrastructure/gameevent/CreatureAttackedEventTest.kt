package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.application.gameevent.CreatureAttackedEventDTO
import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.creature.NoOpCreature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreatureAttackedEventTest {


    @Test
    @Throws(Exception::class)
    fun should_invoke_attack_on_creature() {

        // Given
        val testee = CreatureAttackedEventDTO(
                attackerInstanceId = "InstanceId 1",
                ownerId = "Id 2",
                defenderInstanceId = "InstanceId 3")
        val attacker = NoOpCreature(
                id = "Id 1",
                instanceId = testee.attackerInstanceId,
                hitpoints = 3,
                attack = 2)
        val defender = NoOpCreature(
                id = "Id 3",
                instanceId = testee.defenderInstanceId,
                hitpoints = 3,
                attack = 1)
        val player1 = Player(testee.ownerId, spots = listOf(Spot(id = "spot1", creature = attacker)))
        val player2 = Player("Id 5", spots = listOf(Spot(id = "spot2", creature = defender)))
        val game = Game(
                "Id 4",
                player1 = player1,
                player2 = player2)

        // When
        val updatedGame = testee(game, UUIDGeneratorMock)

        // Then
        updatedGame.printLog()
        val updatedAttacker = updatedGame.findCreature(attacker.instanceId)
                ?: throw Exception("Attacker not present")
        assertThat(updatedAttacker.hitpoints).isEqualTo(2)
        val updatedDefender = updatedGame.findCreature(defender.instanceId)
                              ?: throw Exception("Defender not present")
        assertThat(updatedDefender.hitpoints).isEqualTo(1)
    }
}
