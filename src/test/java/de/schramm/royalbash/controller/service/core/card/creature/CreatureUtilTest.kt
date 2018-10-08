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
        val targetSpot = Spot.builder().build()
        val owner = Player.builder()
                .handcard(card)
                .spot(targetSpot)
                .build()
        val player2 = Player.builder().build()
        val game = Game.builder()
                .player1(owner)
                .player2(player2)
                .build()
        val context = Context(game, owner, targetSpot = targetSpot)

        // When
        val updatedGame = CreatureUtil.spawnCreature(card, context)

        // Then
        val cardInSpot = updatedGame.player1.spots
                .findFirst()
                .map { spot -> spot.creature }
                .map { optional -> optional.get() }
        Assertions.assertThat(cardInSpot)
                .isPresent
                .isEqualTo(Optional.of(card))
    }
}