package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TurnTest {

    @Test
    public void endTurnOf_shouldTogglePlayerOnTurnAndIncreaseCounter() throws GameBrokenException, RuleViolationException {

        // Given
        val playerBlueId = UUID.randomUUID();
        val playerRedId = UUID.randomUUID();
        val turn = Turn.builder()
                .playerBlueId(playerBlueId)
                .playerRedId(playerRedId)
                .currentTurnPlayerId(playerBlueId)
                .counter(0)
                .build();

        // When
        turn.endTurnOf(Player.builder().id(playerBlueId).build());

        // Then
        Assert.assertThat(turn, Matchers.is(
                Turn.builder()
                        .playerBlueId(playerBlueId)
                        .playerRedId(playerRedId)
                        .currentTurnPlayerId(playerRedId)
                        .counter(1)
                        .build()
        ));
    }

    @Test(expected = GameBrokenException.class)
    public void endTurnOf_shouldThrowException_whenGivenUnknownPlayer() throws GameBrokenException, RuleViolationException {

        // Given
        val turn = Turn.builder()
                .playerBlueId(UUID.randomUUID())
                .playerRedId(UUID.randomUUID())
                .build();

        // When Then
        turn.endTurnOf(Player.builder().id(UUID.randomUUID()).build());
    }

    @Test(expected = RuleViolationException.class)
    public void endTurnOf_shouldThrowException_whenGivenPlayerNotOnTurn() throws GameBrokenException, RuleViolationException {

        // Given
        val playerBlueId = UUID.randomUUID();
        val playerRedId = UUID.randomUUID();
        val turn = Turn.builder()
                .playerBlueId(playerBlueId)
                .playerRedId(playerRedId)
                .currentTurnPlayerId(playerBlueId)
                .build();

        // When Then
        turn.endTurnOf(Player.builder().id(playerRedId).build());
    }
}