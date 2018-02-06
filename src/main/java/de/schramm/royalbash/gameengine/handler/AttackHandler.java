package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.InstanceCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.InstanceOwnedByPlayerInstanceOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.InstancesOwnedByOpposingPlayerInstancesChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.AttackableCanAttack;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttackHandler {

    // Repositories
    private final BoardRepository boardRepository;
    private final CardInstanceRepository cardInstanceRepository;

    //Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final InstanceCanAttackBeAttackedChecker instanceCanAttackBeAttackedChecker;
    private final InstanceOwnedByPlayerInstanceOnBoardChecker instanceOwnedByPlayerInstanceOnBoardChecker;
    private final InstancesOwnedByOpposingPlayerInstancesChecker instancesOwnedByOpposingPlayerInstancesChecker;

    @Autowired
    public AttackHandler(
            BoardRepository boardRepository,
            CardInstanceRepository cardInstanceRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            InstanceCanAttackBeAttackedChecker instanceCanAttackBeAttackedChecker,
            InstanceOwnedByPlayerInstanceOnBoardChecker instanceOwnedByPlayerInstanceOnBoardChecker,
            InstancesOwnedByOpposingPlayerInstancesChecker instancesOwnedByOpposingPlayerInstancesChecker
    ) {
        this.boardRepository = boardRepository;
        this.cardInstanceRepository = cardInstanceRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.instanceCanAttackBeAttackedChecker = instanceCanAttackBeAttackedChecker;
        this.instanceOwnedByPlayerInstanceOnBoardChecker = instanceOwnedByPlayerInstanceOnBoardChecker;
        this.instancesOwnedByOpposingPlayerInstancesChecker = instancesOwnedByOpposingPlayerInstancesChecker;
    }

    public Board attackCardInstance(
            UUID boardId,
            UUID attackingInstanceId,
            UUID attackedInstanceId
    ) throws GameEngineException {

        // Fetch domain objects

        Board board = boardRepository.find(boardId);
        CardInstance attackingInstance = cardInstanceRepository.find(attackingInstanceId);
        CardInstance attackedInstance = cardInstanceRepository.find(attackedInstanceId);

        // Apply rules

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(
                board,
                attackingInstance,
                attackedInstance
        );

        instanceCanAttackBeAttackedChecker.checkIfInstanceCanAttack(
                attackingInstance
        );

        instanceCanAttackBeAttackedChecker.checkIfInstanceIsAttackable(
                attackedInstance
        );

        instanceOwnedByPlayerInstanceOnBoardChecker.checkIfInstanceIsOwnedByPlayerInstanceOnBoard(
                board,
                attackingInstance,
                attackedInstance
        );

        instancesOwnedByOpposingPlayerInstancesChecker.checkIfInstancesOwnedByOpposingPlayerInstances(
                board,
                attackingInstance,
                attackedInstance
        );

        // Commit attack

        AttackableCanAttack attacker = (AttackableCanAttack) attackingInstance;

        AttackableCanAttack defender = (AttackableCanAttack) attackedInstance;

        defender.receiveDamage(attacker);

        attacker.receiveDamage(defender);

        if (defender.isDead()) {

            board.buryInstance(defender);

            cardInstanceRepository.delete(defender.getId());
        } else {

            cardInstanceRepository.save(defender);
        }

        if (attacker.isDead()) {

            board.buryInstance(attacker);

            cardInstanceRepository.delete(attacker.getId());
        } else {

            cardInstanceRepository.save(attacker);
        }

        boardRepository.save(board);

        // Return updated Board

        return board;
    }
}
