package de.schramm.royalbash.tdd.core;

import de.schramm.royalbash.tdd.core.card.NoOpCard;
import lombok.val;
import org.assertj.core.data.Index;
import org.junit.Test;

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
        assertThat(hitpoints).isEqualTo(30);
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
        assertThat(cards).hasSize(1);
        assertThat(cards).contains(handcard);
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
        assertThat(cards).contains(handcard1, Index.atIndex(0));
        assertThat(cards).contains(handcard2, Index.atIndex(1));
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
        assertThat(player.getHandcards()).hasSize(0);
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
        assertThat(player.getHandcards()).hasSize(1);
        assertThat(player.getHandcards()).contains(handcard1);
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
        assertThat(player.getHandcards()).hasSize(2);
        assertThat(player.getHandcards()).contains(handcard1, Index.atIndex(0));
        assertThat(player.getHandcards()).contains(handcard3, Index.atIndex(1));
    }
}
