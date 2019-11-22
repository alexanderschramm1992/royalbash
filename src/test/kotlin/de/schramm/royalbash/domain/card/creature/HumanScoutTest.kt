package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HumanScoutTest {

    @Test
    fun `should draw two cards for owner when placed on spot`() {
        // Given
        val testee = HumanScout(
                id = "Id 1",
                instanceId = "InstanceId 1",
                hitpoints = 1,
                attack = 1,
                cost = 1)
        val spot = Spot(id = "Id 2")
        val player1 = Player(id = "Id 3", resources = 1, spots = listOf(spot), deckcards = listOf(testee, testee))
        val player2 = Player(id = "Id 4")
        val game = Game("Id 5", player1 = player1, player2 = player2)
        val context = InvokationOnSpotContext(UUIDGeneratorMock, game, player1, spot)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        updatedGame.printLog()
        Assertions.assertThat(updatedGame.player1.handcards).hasSize(2)
    }
}
