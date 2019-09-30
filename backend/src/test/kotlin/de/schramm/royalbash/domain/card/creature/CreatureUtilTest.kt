package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CreatureUtilTest {

    @Test
    fun should_put_creature_to_player_board() {

        // Given
        val card = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val targetSpot = Spot(id = "spot")
        val owner = Player("Id 2", handcards = listOf(card), spots = listOf(targetSpot))
        val player2 = Player("Id 3")
        val game = Game("Id 4", player1 = owner, player2 = player2)
        val context = Context(game, owner, targetSpot = targetSpot)

        // When
        val updatedGame = card.spawnCreature(context)

        // Then
        val cardInSpot = updatedGame
                .player1
                .spots
                .first()
                .creature
        Assertions.assertThat(cardInSpot)
                .isNotNull
                .isEqualTo(card)
    }
}
