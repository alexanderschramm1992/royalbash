package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.player.PlayerInstance;
import org.springframework.stereotype.Component;

@Component
public class PlayerInstanceOnBoardChecker {

    public void checkIfPlayerInstanceBelongsToBoard(
            PlayerInstance playerInstance,
            Board board
    ) throws GameRuleViolationException {

        if (
                !board.getPlayerBlueInstance().equals(playerInstance)
                        && !board.getPlayerRedInstance().equals(playerInstance)
                ) {

            throw new GameRuleViolationException(
                    String.format(
                            "PlayerInstance %s does not belong to Board %s",
                            playerInstance.getId(),
                            board.getId()
                    )
            );
        }
    }
}
