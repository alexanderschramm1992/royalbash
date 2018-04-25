package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerInstanceHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerInstanceOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SummonHandler {

    // Repositories
    private final CardInstanceRepository cardInstanceRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerInstanceOnBoardChecker playerInstanceOnBoardChecker;
    private final PlayerInstanceHasCardInHandChecker playerInstanceHasCardInHandChecker;

    @Autowired
    public SummonHandler(
            CardInstanceRepository cardInstanceRepository,
            PlayerRepository playerRepository,
            BoardRepository boardRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerInstanceOnBoardChecker playerInstanceOnBoardChecker,
            PlayerInstanceHasCardInHandChecker playerInstanceHasCardInHandChecker
    ) {
        this.cardInstanceRepository = cardInstanceRepository;
        this.playerRepository = playerRepository;
        this.boardRepository = boardRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerInstanceOnBoardChecker = playerInstanceOnBoardChecker;
        this.playerInstanceHasCardInHandChecker = playerInstanceHasCardInHandChecker;
    }

    public Board summonInstance(
            UUID boardId,
            UUID playerInstanceId,
            UUID cardInstanceId
    ) throws GameEngineException {

        // Fetch domain objects

        Board board = boardRepository.find(boardId);
        PlayerInstance playerInstance = playerRepository.find(playerInstanceId);
        CardInstance cardInstance = cardInstanceRepository.find(cardInstanceId);

        // Apply rules

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(
                board,
                playerInstance,
                cardInstance
        );

        playerInstanceOnBoardChecker.checkIfPlayerInstanceBelongsToBoard(
                playerInstance,
                board
        );

        playerInstanceHasCardInHandChecker.checkIfPlayerInstanceHasCardInHand(
                playerInstance,
                cardInstance
        );

        // Summon Instance

        playerInstance.removeCardInstanceFromHand(cardInstance);
        board.summonInstance(playerInstance.getId(), cardInstance);

        // Save changes

        boardRepository.save(board);
        playerRepository.save(playerInstance);
        cardInstanceRepository.save(cardInstance);

        // Return updated Board

        return board;
    }
}
