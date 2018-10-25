package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RemoveCreatureEffectTest {

    @Test
    fun should_remove_target_creature_from_spot_an_add_it_to_deposit() {

        // Given
        val testee = RemoveCreatureEffect()
        val creature = CreatureMock("Id 1")
        val spot = Spot(id = "spot", creature = creature)
        val owner = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3")
        val game = Game("Id 4", player1 = owner, player2 = player2)
        val context = Context(game = game, owner = owner, targetCreature = creature)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isNotNull
        assertThat(updatedGame.player1.findCreature(creature)).isNotPresent
        assertThat(updatedGame.player1.depositcards)
                .hasSize(1)
                .contains(creature)
    }

}
