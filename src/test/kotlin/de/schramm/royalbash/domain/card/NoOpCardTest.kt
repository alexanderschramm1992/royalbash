package de.schramm.royalbash.domain.card

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoOpCardTest {

    @Test
    fun should_not_alter_game() {

        // Given
        val testee = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val player1 = Player("Id 2", handcards = listOf(testee))
        val player2 = Player("Id 3")
        val game = Game("Id 4", player1 = player1, player2 = player2)
        val context = Context(game, player1, player2)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isEqualTo(game)
    }

}
