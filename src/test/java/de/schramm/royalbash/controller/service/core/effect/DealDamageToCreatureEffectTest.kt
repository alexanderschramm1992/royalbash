package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.Spot
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class DealDamageToCreatureEffectTest {

    @Test
    @Throws(Exception::class)
    fun should_deal_damage_to_target_creature() {

        // Given
        val testee = DealDamageToCreatureEffect.builder()
                .amountOfDamage(2)
                .build()
        val creature = CreatureMock(
                "Id 1",
                hitpoints = 5
        )
        val owner = Player.builder().build()
        val game = Game.builder()
                .player1(Player.builder()
                        .spot(Spot.builder()
                                .creature(creature)
                                .build())
                        .build())
                .player2(owner)
                .build()
        val context = Context(
                game,
                owner,
                targetCreature = creature
        )

        // When
        val updatedGame = testee.invoke(context)

        // Then
        assertThat(updatedGame).isNotNull
        val updatedCreature = updatedGame.findCreature("Id 1")
                .orElseThrow { Exception("Creature not present") }
        assertThat(updatedCreature.hitpoints).isEqualTo(3)
    }
}
