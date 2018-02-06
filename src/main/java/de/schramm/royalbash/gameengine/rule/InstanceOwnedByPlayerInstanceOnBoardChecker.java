package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.CardInstance;
import org.springframework.stereotype.Component;

@Component
public class InstanceOwnedByPlayerInstanceOnBoardChecker {

    public void checkIfInstanceIsOwnedByPlayerInstanceOnBoard(
            Board board,
            CardInstance... cardInstances
    ) throws GameRuleViolationException {

        for (CardInstance cardInstance : cardInstances) {

            if (cardInstance.equals(board.getPlayerBlueInstance())
                    || cardInstance.equals(board.getPlayerRedInstance())
                    ) {

                // instance is one of the PlayerInstances
                // do nothing
            } else if (
                    board.getBlueInstanceSet().contains(cardInstance)
                            || board.getRedInstanceSet().contains(cardInstance)
                    ) {

                // instance is owned by one of the PlayerInstances
                // do nothing
            } else {

                throw new GameRuleViolationException(
                        String.format(
                                "Instance %s is not or is not owned by PlayerInstances on Board %s",
                                cardInstance.getId(),
                                board.getId()
                        )
                );
            }
        }
    }
}
