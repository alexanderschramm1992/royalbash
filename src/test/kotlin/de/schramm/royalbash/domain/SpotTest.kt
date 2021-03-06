package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.card.creature.NoOpCreature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SpotTest {

    @Test
    fun should_deliver_placed_creature() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Spot(id = "spot")

        // When
        val spot = testee.place(creature)

        // Then
        assertThat(spot.creature).isEqualTo(creature)
    }

    @Test
    fun should_update_creature() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val otherCreature = creature.copy(hitpoints = 15)
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.updateCreature(creature to otherCreature)

        // Then
        assertThat(spot.creature).isEqualTo(otherCreature)
    }

    @Test
    fun should_not_update_creature() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Spot(id = "spot")

        // When
        val spot = testee.updateCreature(creature to creature)

        // Then
        assertThat(spot.creature).isNull()
    }

    @Test
    fun should_remove_creature() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.buryCreature(creature)

        // Then
        assertThat(spot.creature).isNull()
    }

    @Test
    fun should_not_remove_creature() {

        // Given+ßjn0m
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val otherCreature = NoOpCreature("Id 2", instanceId = "InstanceId 2", hitpoints = 15)
        val testee = Spot(id = "spot", creature = creature)

        // When
        val spot = testee.buryCreature(otherCreature)

        // Then
        assertThat(spot.creature).isEqualTo(creature)
    }
}
