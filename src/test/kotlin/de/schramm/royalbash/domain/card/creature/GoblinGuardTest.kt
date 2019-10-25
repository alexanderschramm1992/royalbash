package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.spell.NoOpSpell
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
        val player1 = Player("Id 2", resources = 1, spots = listOf(spot))
        val player2 = Player("Id 3", handcards = listOf(NoOpSpell(id = "Id 1", instanceId = "InstanceId 1"),
                                                        NoOpSpell(id = "Id 5", instanceId = "InstanceId 5")))
        val game = Game("Id 6", player1 = player1, player2 = player2)
        val context = InvokationOnSpotContext(UUIDGeneratorMock, game, player1, spot)

        // When
        val updatedGame = testee(context)

        // Then
        updatedGame.printLog()
        assertThat(updatedGame.player2.handcards).hasSize(1)
    }

}
