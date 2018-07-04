package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
import java.util.stream.Stream;

@Builder
@Getter
@ToString
public class Board {

    private final UUID id;
    private final Turn turn;
    private final Player playerBlue;
    private final Player playerRed;

    public void bury(Summoning summoning) {

        playerBlue.bury(summoning);
        playerRed.bury(summoning);
    }

    public void endTurnOf(Player player) throws GameBrokenException, RuleViolationException {
        turn.endTurnOf(player);
    }

    Player findOpponent(Player player) throws GameBrokenException {
        Stream.of(playerBlue, playerRed)
                .filter(playerOnBoard -> playerOnBoard.equals(player))
                .findFirst()
                .orElseThrow(() -> new GameBrokenException(String.format(
                        "Player %s is not managed by board %s",
                        player.getId(),
                        id
                )));

        return playerBlue.equals(player) ? playerRed : playerBlue;
    }
}
