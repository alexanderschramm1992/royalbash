package de.schramm.royalbash.core.domain.game.board;

import de.schramm.royalbash.core.domain.game.board.player.Player;
import de.schramm.royalbash.core.exception.GameBrokenException;
import de.schramm.royalbash.core.exception.RuleViolationException;
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

    public void endTurnOf(Player player) throws GameBrokenException, RuleViolationException {
        turn.endTurnOf(player);
    }

    public Player findOpponent(Player player) throws GameBrokenException {
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

    public void purge() {
        Stream.of(playerBlue, playerRed).forEach(Player::purge);
    }
}
