package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class DealDamageToCreatureEffectTest {

    @Test
    @Throws(Exception::class)
    fun should_deal_damage_to_target_creature() {

        // Given
        val testee = DealDamageToCreatureEffect(2)
        val creature = CreatureMock("Id 1", hitpoints = 5)
        val owner = Player("Id 2")
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 3", spots = listOf(spot))
        val game = Game("Id 3", player1 = player1, player2 = owner)
        val context = Context(
                game,
                owner,
                targetCreature = creature)

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isNotNull
        val updatedCreature = updatedGame.findCreature("Id 1")
                .orElseThrow { Exception("Creature not present") }
        assertThat(updatedCreature.hitpoints).isEqualTo(3)
    }
}
