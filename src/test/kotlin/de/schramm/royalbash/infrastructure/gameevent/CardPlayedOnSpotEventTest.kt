package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.application.gameevent.CardPlayedOnSpotEventDTO
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.spell.NoOpSpell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPlayedOnSpotEventTest {

    @Test
    fun should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnSpotEventDTO(
                cardInstanceId = "InstanceId 1",
                ownerId = "Id 2",
                targetSpotId = "Id 3")
        val card = NoOpSpell(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot("Id 3")
        val player1 = Player("Id 2", handcards = listOf(card), spots = listOf(spot))
        val game = Game(
                "Id 4",
                player1 = player1,
                player2 = Player("Id 5"))

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.handcards).hasSize(0)
    }
}
