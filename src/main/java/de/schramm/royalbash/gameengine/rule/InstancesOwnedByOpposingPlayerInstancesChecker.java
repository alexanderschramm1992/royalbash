package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.CardInstance;
import org.springframework.stereotype.Component;

@Component
public class InstancesOwnedByOpposingPlayerInstancesChecker {

    public void checkIfInstancesOwnedByOpposingPlayerInstances(
            Board board,
            CardInstance instance1,
            CardInstance instance2
    ) throws GameRuleViolationException {

        if (
                (
                        board.getPlayerBlueInstance().equals(instance1)
                                || board.getBlueInstanceSet().contains(instance1)
                ) && (
                        board.getPlayerRedInstance().equals(instance2)
                                || board.getRedInstanceSet().contains(instance2)
                )

                ) {

            // Instance instance1 belongs to blue and Instance instance2 to red
            // do nothing
        } else if (
                (
                        board.getPlayerBlueInstance().equals(instance2)
                                || board.getBlueInstanceSet().contains(instance2)
                ) && (
                        board.getPlayerRedInstance().equals(instance1)
                                || board.getRedInstanceSet().contains(instance1)
                )
                ) {

            // Instance instance1 belongs to red and Instance instance2 to blue
            // do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Instances %s and %s are not or are not owned by opposing PlayerInstances of Board %s",
                            instance1.getId(),
                            instance2.getId(),
                            board.getId()
                    )
            );
        }
    }
}
