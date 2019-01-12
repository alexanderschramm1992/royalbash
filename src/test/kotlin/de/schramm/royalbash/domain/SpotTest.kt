package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class SpotTest {

    @Test
    fun should_deliver_placed_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val testee = Spot(id = "spot")

        // When
        val spot = testee.place(creature)

        // Then
        assertThat(spot.getCreature()).isEqualTo(Optional.of(creature))
    }

    @Test
    fun should_update_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val otherCreature = creature.copy(hitpoints = 15)
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.updateCreature(creature, otherCreature)

        // Then
        assertThat(spot.getCreature()).isEqualTo(Optional.of(otherCreature))
    }

    @Test
    fun should_not_update_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val testee = Spot(id = "spot")

        // When
        val spot = testee.updateCreature(creature, creature)

        // Then
        assertThat(spot.getCreature()).isNotPresent
    }

    @Test
    fun should_remove_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.removeCreature(creature)

        // Then
        assertThat(spot.getCreature()).isNotPresent
    }

    @Test
    fun should_not_remove_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val otherCreature = CreatureMock("Id 2", hitpoints = 15)
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.removeCreature(otherCreature)

        // Then
        assertThat(spot.getCreature()).isEqualTo(Optional.of(creature))
    }
}