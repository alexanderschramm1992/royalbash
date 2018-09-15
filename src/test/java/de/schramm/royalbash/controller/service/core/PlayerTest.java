package de.schramm.royalbash.controller.service.core;

import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import de.schramm.royalbash.controller.service.core.creature.NoOpCreature;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void should_deliver_hitpoints() {

        // Given
        val testee = Player.builder()
                .hitpoints(30)
                .build();

        // When
        val hitpoints = testee.getHitpoints();

        // Then
        Assertions.assertThat(hitpoints).isEqualTo(30);
    }

    @Test
    public void should_change_hitpoints() {

        // Given
        val testee = Player.builder()
                .hitpoints(30)
                .build();

        // When
        val player = testee.setHitpoints(10);

        // Then
        assertThat(player.getHitpoints()).isEqualTo(10);
    }

    @Test
    public void should_deliver_handcards() {

        // Given
        val handcard = NoOpCard.builder().build();
        val testee = Player.builder()
                .handcard(handcard)
                .build();

        // When
        val cards = testee.getHandcards();

        // Then
        Assertions.assertThat(cards.collect(Collectors.toList())).hasSize(1);
        Assertions.assertThat(cards.collect(Collectors.toList())).contains(handcard);
    }

    @Test
    public void should_deliver_handcards_in_order() {

        // Given
        val handcard1 = NoOpCard.builder()
                .name("Card 1")
                .build();
        val handcard2 = NoOpCard.builder()
                .name("Card 2")
                .build();
        val testee = Player.builder()
                .handcard(handcard1)
                .handcard(handcard2)
                .build();

        // When
        val cards = testee.getHandcards();

        // Then
        Assertions.assertThat(cards.collect(Collectors.toList())).contains(handcard1, Index.atIndex(0));
        Assertions.assertThat(cards.collect(Collectors.toList())).contains(handcard2, Index.atIndex(1));
    }

    @Test
    public void should_remove_handcard() {

        // Given
        val handcard = NoOpCard.builder().build();
        val testee = Player.builder()
                .handcard(handcard)
                .build();

        // When
        val player = testee.removeHandcard(handcard);

        // Then
        assertThat(player.getHandcards().collect(Collectors.toList())).hasSize(0);
    }

    @Test
    public void should_not_remove_handcard_if_it_cannot_be_found() {

        // Given
        val handcard1 = NoOpCard.builder().name("Card 1").build();
        val handcard2 = NoOpCard.builder().name("Card 2").build();
        val testee = Player.builder()
                .handcard(handcard1)
                .build();

        // When
        val player = testee.removeHandcard(handcard2);

        // Then
        assertThat(player.getHandcards().collect(Collectors.toList())).hasSize(1);
        assertThat(player.getHandcards().collect(Collectors.toList())).contains(handcard1);
    }

    @Test
    public void should_retain_order_when_removing_card() {

        // Given
        val handcard1 = NoOpCard.builder().name("Card 1").build();
        val handcard2 = NoOpCard.builder().name("Card 2").build();
        val handcard3 = NoOpCard.builder().name("Card 3").build();
        val testee = Player.builder()
                .handcard(handcard1)
                .handcard(handcard2)
                .handcard(handcard3)
                .build();

        // When
        val player = testee.removeHandcard(handcard2);

        // Then
        assertThat(player.getHandcards().collect(Collectors.toList())).hasSize(2);
        assertThat(player.getHandcards().collect(Collectors.toList())).contains(handcard1, Index.atIndex(0));
        assertThat(player.getHandcards().collect(Collectors.toList())).contains(handcard3, Index.atIndex(1));
    }

    @Test
    public void should_remove_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val spot = Spot.builder()
                .creature(creature)
                .build();
        val testee = Player.builder()
                .spot(spot)
                .build();

        // When
        val player = testee.removeCreature(creature);

        // Then
        val creaturesOfPlayer = player.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Assertions.assertThat(creaturesOfPlayer).isEmpty();
    }

    @Test
    public void should_not_remove_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val spot = Spot.builder()
                .creature(creature)
                .build();
        val testee = Player.builder()
                .spot(spot)
                .build();

        // When
        val player = testee.removeCreature(
                NoOpCreature.builder()
                        .hitpoints(2)
                        .build()
        );

        // Then
        val creaturesOfPlayer = player.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Assertions.assertThat(creaturesOfPlayer).hasSize(1);
        Assertions.assertThat(creaturesOfPlayer).contains(creature);
    }

    @Test
    public void should_update_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val spot = Spot.builder()
                .creature(creature)
                .build();
        val testee = Player.builder()
                .spot(spot)
                .build();
        val updatedCreature = creature.toBuilder()
                .hitpoints(12)
                .build();

        // When
        val player = testee.updateCreature(creature, updatedCreature);

        // Then
        val creaturesOfPlayer = player.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Assertions.assertThat(creaturesOfPlayer).contains(updatedCreature);
        Assertions.assertThat(creaturesOfPlayer).doesNotContain(creature);
    }

    @Test
    public void should_not_update_creature() {

        // Given
        val creature = NoOpCreature.builder().build();
        val spot = Spot.builder()
                .build();
        val testee = Player.builder()
                .spot(spot)
                .build();

        // When
        val player = testee.updateCreature(creature, creature);

        // Then
        val creaturesOfPlayer = player.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Assertions.assertThat(creaturesOfPlayer).doesNotContain(creature);
    }
}
