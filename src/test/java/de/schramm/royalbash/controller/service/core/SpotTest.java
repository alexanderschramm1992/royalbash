package de.schramm.royalbash.controller.service.core;

import de.schramm.royalbash.controller.service.core.creature.NoOpCreature;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpotTest {

    @Test
    public void should_deliver_placed_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val testee = Spot.builder().build();

        // When
        val spot = testee.place(creature);

        // Then
        assertThat(spot.getCreature().isPresent()).isTrue();
        assertThat(spot.getCreature().get()).isEqualTo(creature);
    }

    @Test
    public void should_update_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val otherCreature = creature.toBuilder()
                .hitpoints(15)
                .build();
        val testee = Spot.builder()
                .creature(creature)
                .build();

        // When
        val spot = testee.updateCreature(creature, otherCreature);

        // Then
        assertThat(spot.getCreature().isPresent()).isTrue();
        assertThat(spot.getCreature().get()).isEqualTo(otherCreature);
    }

    @Test
    public void should_not_update_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val testee = Spot.builder().build();

        // When
        val spot = testee.updateCreature(creature, creature);

        // Then
        assertThat(spot.getCreature().isPresent()).isFalse();
    }

    @Test
    public void should_remove_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val testee = Spot.builder()
                .creature(creature)
                .build();

        // When
        val spot = testee.removeCreature(creature);

        // Then
        assertThat(spot.getCreature().isPresent()).isFalse();
    }

    @Test
    public void should_not_remove_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val otherCreature = NoOpCreature.builder()
                .hitpoints(15)
                .build();
        val testee = Spot.builder()
                .creature(creature)
                .build();

        // When
        val spot = testee.removeCreature(otherCreature);

        // Then
        assertThat(spot.getCreature().isPresent()).isTrue();
        assertThat(spot.getCreature().get()).isEqualTo(creature);
    }
}