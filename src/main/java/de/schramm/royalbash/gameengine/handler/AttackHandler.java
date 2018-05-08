package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SumoningCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.persistence.game.GameRepository;
import de.schramm.royalbash.persistence.summoning.SummoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttackHandler {

    // Repositories
    private final GameRepository gameRepository;
    private final SummoningRepository summoningRepository;

    //Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker;
    private final SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker;
    private final SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker;

    @Autowired
    public AttackHandler(
            GameRepository gameRepository,
            SummoningRepository summoningRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker,
            SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker,
            SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker
    ) {
        this.gameRepository = gameRepository;
        this.summoningRepository = summoningRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.sumoningCanAttackBeAttackedChecker = sumoningCanAttackBeAttackedChecker;
        this.summoningOwnedByPlayerOnBoardChecker = summoningOwnedByPlayerOnBoardChecker;
        this.summoningsOwnedByOpposingPlayersChecker = summoningsOwnedByOpposingPlayersChecker;
    }

    public Game attackSummoning(
            UUID gameId,
            UUID attackingSummoningId,
            UUID attackedSummoningId
    ) throws GameEngineException {

        // Fetch domain objects

        Game game = gameRepository.find(gameId);
        Summoning attackingSummoning = summoningRepository.find(attackingSummoningId);
        Summoning attackedSummoning = summoningRepository.find(attackedSummoningId);

        // Apply rules

        requiredDomainObjectChecker.check(
                game,
                attackingSummoning,
                attackedSummoning
        );
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
            summoningRepository.delete(attackedSummoning.getId());
        } else {

            summoningRepository.save(attackedSummoning);
        }

        if (attackingSummoning.isDead()) {

            game.getBoard().bury(attackingSummoning);
            summoningRepository.delete(attackingSummoning.getId());
        } else {

            summoningRepository.save(attackingSummoning);
        }

        gameRepository.save(game);

        return gameRepository.find(gameId);
    }
}
