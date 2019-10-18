package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.creature.CreatureMock
import de.schramm.royalbash.application.gameevent.PlayerAttackedEventDTO
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerAttackedEventTest {

    @Test
    fun should_invoke_attack_on_player() {

        // Given
        val creature = CreatureMock(
                id = "Id 1",
                instanceId = "InstanceId 1",
                attack = 2
        )
        val player1 = Player("Id 2", spots = listOf(Spot(id = "spot", creature = creature)))
        val player2 = Player("Id 4", hitpoints = 5)
        val game = Game(
                "Id 3",
                player1 = player1,
                player2 = player2)
        val testee = PlayerAttackedEventDTO("Id 1", "Id 2")

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame.player2.hitpoints).isEqualTo(3)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID,
                "${creature.name} of ${player1.name} has attacked ${player2.name}"))
    }
}
