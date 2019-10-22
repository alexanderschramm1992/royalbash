package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.creature.CreatureMock
import de.schramm.royalbash.application.gameevent.CreatureAttackedEventDTO
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreatureAttackedEventTest {


    @Test
    @Throws(Exception::class)
    fun should_invoke_attack_on_creature() {

        // Given
        val testee = CreatureAttackedEventDTO(
                attackerId = "Id 1",
                ownerId = "Id 2",
                defenderId = "Id 3")
        val attacker = CreatureMock(
                id = "Id 1",
                instanceId = "InstamceId 1",
                hitpoints = 3,
                attack = 2)
        val defender = CreatureMock(
                id = "Id 3",
                instanceId = "InstanceId 3",
                hitpoints = 3,
                attack = 1)
        val player1 = Player("Id 2", spots = listOf(Spot(id = "spot1", creature = attacker)))
        val player2 = Player("Id 5", spots = listOf(Spot(id = "spot2", creature = defender)))
        val game = Game(
                "Id 4",
                player1 = player1,
                player2 = player2)

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        val updatedAttacker = updatedGame.findCreature("Id 1")
                ?: throw Exception("Attacker not present")
        assertThat(updatedAttacker.hitpoints).isEqualTo(2)
        val updatedDefender = updatedGame.findCreature("Id 3")
                ?: throw Exception("Defender not present")
        assertThat(updatedDefender.hitpoints).isEqualTo(1)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID,
                "${attacker.name} of ${player1.name} has attacked ${defender.name} of ${player2.name}"))
    }
}
