package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.*

class CreatureUtilTest {

    @Test
    fun should_put_creature_to_player_board() {

        // Given
        val card = CreatureMock("Id 1")
        val targetSpot = Spot()
        val owner = Player("Id 2", handcards = listOf(card), spots = listOf(targetSpot))
        val player2 = Player("Id 3")
        val game = Game("Id 4", player1 = owner, player2 = player2)
        val context = Context(game, owner, targetSpot = targetSpot)

        // When
        val updatedGame = CreatureUtil.spawnCreature(card, context)

        // Then
        val cardInSpot = updatedGame
                .player1
                .spots
                .first()
                .getCreature()
        Assertions.assertThat(cardInSpot)
                .isPresent
                .isEqualTo(Optional.of(card))
    }
}