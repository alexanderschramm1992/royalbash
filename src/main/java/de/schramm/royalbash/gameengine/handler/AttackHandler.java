package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Summoning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackHandler {

    private final SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker;
    private final SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker;

    @Autowired
    public AttackHandler(
            SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker,
            SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker
    ) {
        this.summoningOwnedByPlayerOnBoardChecker = summoningOwnedByPlayerOnBoardChecker;
        this.summoningsOwnedByOpposingPlayersChecker = summoningsOwnedByOpposingPlayersChecker;
    }

    public Game attackSummoning(
            Game game,
            Summoning attackingSummoning,
            Summoning attackedSummoning
    ) throws GameEngineException {

        summoningOwnedByPlayerOnBoardChecker.check(
                game.getBoard(),
                attackingSummoning,
                attackedSummoning
        );
        summoningsOwnedByOpposingPlayersChecker.check(
                game.getBoard(),
                attackingSummoning,
                attackedSummoning
        );

        // Commit attack

        attackedSummoning.reduceCurrentHealth(attackingSummoning.getCurrentStrength());
        attackingSummoning.reduceCurrentHealth(attackedSummoning.getCurrentStrength());

        if (attackedSummoning.getCurrentHealth() == 0) {

            game.getBoard().bury(attackedSummoning);
        }

        if (attackingSummoning.getCurrentHealth() == 0) {

            game.getBoard().bury(attackingSummoning);
        }

        return game;
    }
}
