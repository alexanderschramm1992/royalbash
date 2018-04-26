package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SumoningCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.summoning.SummoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttackHandler {

    // Repositories
    private final BoardRepository boardRepository;
    private final SummoningRepository summoningRepository;

    //Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker;
    private final SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker;
    private final SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker;

    @Autowired
    public AttackHandler(
            BoardRepository boardRepository,
            SummoningRepository summoningRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            SumoningCanAttackBeAttackedChecker sumoningCanAttackBeAttackedChecker,
            SummoningOwnedByPlayerOnBoardChecker summoningOwnedByPlayerOnBoardChecker,
            SummoningsOwnedByOpposingPlayersChecker summoningsOwnedByOpposingPlayersChecker
    ) {
        this.boardRepository = boardRepository;
        this.summoningRepository = summoningRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.sumoningCanAttackBeAttackedChecker = sumoningCanAttackBeAttackedChecker;
        this.summoningOwnedByPlayerOnBoardChecker = summoningOwnedByPlayerOnBoardChecker;
        this.summoningsOwnedByOpposingPlayersChecker = summoningsOwnedByOpposingPlayersChecker;
    }

    public Board attackCardInstance(
            UUID boardId,
            UUID attackingSummoningId,
            UUID attackedSumoningId
    ) throws GameEngineException {

        // Fetch domain objects

        Board board = boardRepository.find(boardId);
        Summoning attackingSummoning = summoningRepository.find(attackingSummoningId);
        Summoning attackedSummoning = summoningRepository.find(attackedSumoningId);

        // Apply rules

        requiredDomainObjectChecker.check(
                board,
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
                board,
                attackingSummoning,
                attackedSummoning
        );
        summoningsOwnedByOpposingPlayersChecker.check(
                board,
                attackingSummoning,
                attackedSummoning
        );

        // Commit attack

        attackedSummoning.receiveDamage(attackingSummoning);
        attackingSummoning.receiveDamage(attackedSummoning);

        if (attackedSummoning.isDead()) {

            board.bury(attackedSummoning);
            summoningRepository.delete(attackedSummoning.getId());
        } else {

            summoningRepository.save(attackedSummoning);
        }

        if (attackingSummoning.isDead()) {

            board.bury(attackingSummoning);
            summoningRepository.delete(attackingSummoning.getId());
        } else {

            summoningRepository.save(attackingSummoning);
        }

        boardRepository.save(board);

        // Return updated Board

        return board;
    }
}
