package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerOnBoardChecker {

    public void check(
            Player player,
            Board board
    ) throws GameRuleViolationException {

        if (!board.getPlayerBlue().equals(player) && !board.getPlayerRed().equals(player)) {

            throw new GameRuleViolationException(
                    String.format(
                            "Player %s does not belong to Board %s",
                            player.getId(),
                            board.getId()
                    )
            );
        }
    }
}
