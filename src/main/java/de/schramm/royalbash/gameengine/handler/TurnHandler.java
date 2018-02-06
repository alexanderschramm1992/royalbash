package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;
import de.schramm.royalbash.persistence.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TurnHandler {

    // Repositories
    private final BoardRepository boardRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;

    @Autowired
    public TurnHandler(
            BoardRepository boardRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker
    ) {
        this.boardRepository = boardRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
    }

    public Turn getTurn(UUID boardId) throws DomainObjectDoesNotExistException {

        // Fetch domain object

        Board board = boardRepository.find(boardId);

        // Apply rule

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(board);

        // Fetch nested domain object

        Turn turn = board.getTurn();

        // Apply rule again

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(turn);

        // Return Board

        return turn;
    }

    public void endTurn(UUID boardId) throws DomainObjectDoesNotExistException {

        // Fetch domain object

        Board board = boardRepository.find(boardId);

        // Apply rule

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(board);

        // Fetch nested domain object

        Turn turn = board.getTurn();

        // Apply rule again

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(turn);

        // Increase Turn counter and swap active PlayerInstance

        board.getTurn().increaseTurnCounter();
        board.getTurn().swapPlayerInstance(board);
    }
}
