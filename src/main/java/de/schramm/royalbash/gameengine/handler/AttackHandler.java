package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.gameengine.rule.SumoningCanAttackBeAttackedChecker;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Summoning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackHandler {

    private final SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker;
    private final SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker;
    private final SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker;

    @Autowired
    public AttackHandler(
            SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker,
            SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker,
            SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker
    ) {
        this.sumoningCanAttackBeAttackedChecker = sumoningCanAttackBeAttackedChecker;
        this.summoningOwnedByPlayerOnBoardChecker = summoningOwnedByPlayerOnBoardChecker;
        this.summoningsOwnedByOpposingPlayersChecker = summoningsOwnedByOpposingPlayersChecker;
    }

    public Game attackSummoning(
            Game game,
            Summoning attackingSummoning,
            Summoning attackedSummoning
    ) throws GameEngineException {

        sumoningCanAttackBeAttackedChecker.checkIfInstanceCanAttack(
                attackingSummoning
        );
        sumoningCanAttackBeAttackedChecker.checkIfInstanceIsAttackable(
                attackedSummoning
        );
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

        attackedSummoning.receiveDamage(attackingSummoning);
        attackingSummoning.receiveDamage(attackedSummoning);

        if (attackedSummoning.isDead()) {

            game.getBoard().bury(attackedSummoning);
        }

        if (attackingSummoning.isDead()) {

            game.getBoard().bury(attackingSummoning);
        }

        return game;
    }
}
