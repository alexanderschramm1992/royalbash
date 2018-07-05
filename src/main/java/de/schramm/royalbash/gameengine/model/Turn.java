package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
import java.util.stream.Stream;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Turn {

    private int counter;
    private UUID currentTurnPlayerId;
    private UUID playerBlueId;
    private UUID playerRedId;

    void endTurnOf(Player player) throws RuleViolationException, GameBrokenException {

        checkPlayerId(player.getId());

        if (currentTurnPlayerId.equals(player.getId())) {
            counter += 1;
            currentTurnPlayerId = playerBlueId.equals(player.getId()) ? playerRedId : playerBlueId;
        } else {
            throw new RuleViolationException(String.format("Player %s does not have turn", player.getId()));
        }
    }

    private void checkPlayerId(UUID playerId) throws GameBrokenException {

        Stream.of(playerBlueId, playerRedId)
                .filter(id -> id.equals(playerId))
                .findFirst()
                .orElseThrow(
                        () -> new GameBrokenException(
                                String.format("Player %s is not managed by Turn object", playerId)
                        )
                );
    }
}
