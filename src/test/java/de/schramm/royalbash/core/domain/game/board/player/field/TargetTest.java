package de.schramm.royalbash.core.domain.game.board.player.field;

import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class TargetTest {

    @Test
    public void summon_shouldOccupyTargetWithSummoning_whenSummoningIsUnoccupied() {

        // Given
        val summoning = mock(Summoning.class);
        Target testee = new Target(UUID.randomUUID(), null);

        // When
        testee = testee.summon(summoning);

        // Then
        Assert.assertThat(testee.getSummoning(), Matchers.is(summoning));
    }

    @Test
    public void bury_shouldRemoveSummoningFromTarget_whenTargetIsOccupied() {

        // Given
        val summoning = mock(Summoning.class);
        Target testee = new Target(UUID.randomUUID(), summoning);

        // When
        testee = testee.bury(summoning);

        // Then
        Assert.assertThat(testee.getSummoning(), Matchers.nullValue());
    }
}