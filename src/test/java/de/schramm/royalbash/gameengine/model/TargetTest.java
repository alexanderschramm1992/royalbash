package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TargetTest {

    @Test
    public void summon_shouldOccupyTargetWithSummoning_whenSummoningIsUnoccupied() throws RuleViolationException {

        // Given
        val summoning = mock(Summoning.class);
        val target = Target.builder().build();

        // When
        target.summon(summoning);

        // Then
        Assert.assertThat(target.getSummoning(), Matchers.is(summoning));
    }

    @Test(expected = RuleViolationException.class)
    public void summon_shouldThrowException_whenTargetOccupied() throws RuleViolationException {

        // Given
        val target = Target.builder()
                .summoning(mock(Summoning.class))
                .build();

        // When Then
        target.summon(mock(Summoning.class));
    }

    @Test
    public void bury_shouldRemoveSummoningFromTarget_whenTargetIsOccupied() throws RuleViolationException {

        // Given
        val summoning = mock(Summoning.class);
        val target = Target.builder()
                .summoning(summoning)
                .build();

        // When
        target.bury(summoning);

        // Then
        Assert.assertThat(target.getSummoning(), Matchers.nullValue());
    }

    @Test(expected = RuleViolationException.class)
    public void bury_shouldThrowException_whenSummoningIsNotOccupied() throws RuleViolationException {

        // Given
        val target = Target.builder().build();

        // When Then
        target.bury(mock(Summoning.class));
    }

    @Test(expected = RuleViolationException.class)
    public void bury_shouldThrowException_whenSummoningIsDifferentToOccupingOne() throws RuleViolationException {

        // Given
        val target = Target.builder()
                .summoning(mock(Summoning.class))
                .build();

        // When Then
        target.bury(mock(Summoning.class));
    }
}