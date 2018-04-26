package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Summoning;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummoningsOwnedByOpposingPlayersChecker {

    public void check(
            Board board,
            Summoning summoning1,
            Summoning summoning2
    ) throws GameRuleViolationException {

        List<Summoning> playerBlueSummonings = board.getPlayerBlue().getSummonings();
        List<Summoning> playerRedSummonings = board.getPlayerRed().getSummonings();

        if (playerBlueSummonings.contains(summoning1) && playerRedSummonings.contains(summoning2)) {

            // Instance instance1 belongs to blue and Instance instance2 to red
            // do nothing
        } else if (playerBlueSummonings.contains(summoning2) &&  playerRedSummonings.contains(summoning1)) {

            // Instance instance1 belongs to red and Instance instance2 to blue
            // do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Summonings %s and %s are not owned by opposing Players of Board %s",
                            summoning1.getId(),
                            summoning2.getId(),
                            board.getId()
                    )
            );
        }
    }
}
