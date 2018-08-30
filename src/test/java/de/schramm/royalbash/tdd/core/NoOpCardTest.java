package de.schramm.royalbash.tdd.core;

import de.schramm.royalbash.tdd.core.card.NoOpCard;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoOpCardTest {

    @Test
    public void should_deliver_name() {

        // Given
        val testee = NoOpCard.builder()
                .name("Card")
                .build();

        // When
        val name = testee.getName();

        // Then
        assertThat(name).isEqualTo("Card");
    }
}