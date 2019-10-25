package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.application.gameevent.CardDrawnEventDTO
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.card.spell.NoOpSpell
import de.schramm.royalbash.infrastructure.gameevent.UUIDGeneratorMock.MOCK_ID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDrawnEventTest {

    @Test
    fun should_invoke_draw_card() {

        // Given
        val card = NoOpSpell(id = "Id 1", instanceId = "InstanceId 1")
        val player1 = Player("Id 2", deckcards = listOf(card))
        val game = Game(
                "Id 3",
                player1 = player1,
                player2 = Player("Id 4"))
        val testee = CardDrawnEventDTO("Id 2", 1)

        // When
        val updatedGame = testee.invoke(game, UUIDGeneratorMock)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.handcards).hasSize(1)
        assertThat(updatedGame.logs).endsWith(Log(MOCK_ID, "${player1.name} has drawn a card"))
    }
}
