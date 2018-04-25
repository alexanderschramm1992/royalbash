package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.DeckInstanceOwnedByPlayerInstanceChecker;
import de.schramm.royalbash.gameengine.rule.PlayerInstanceCanDrawAnotherCardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.deck.DeckInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.deck.instance.DeckInstanceRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DrawHandler {

    // Repositories
    private final DeckInstanceRepository deckInstanceRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerInstanceCanDrawAnotherCardChecker playerInstanceCanDrawAnotherCardChecker;
    private final DeckInstanceOwnedByPlayerInstanceChecker deckInstanceOwnedByPlayerInstanceChecker;

    @Autowired
    public DrawHandler(
            DeckInstanceRepository deckInstanceRepository,
            PlayerRepository playerRepository,
            BoardRepository boardRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerInstanceCanDrawAnotherCardChecker playerInstanceCanDrawAnotherCardChecker,
            DeckInstanceOwnedByPlayerInstanceChecker deckInstanceOwnedByPlayerInstanceChecker
    ) {
        this.deckInstanceRepository = deckInstanceRepository;
        this.playerRepository = playerRepository;
        this.boardRepository = boardRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerInstanceCanDrawAnotherCardChecker = playerInstanceCanDrawAnotherCardChecker;
        this.deckInstanceOwnedByPlayerInstanceChecker = deckInstanceOwnedByPlayerInstanceChecker;
    }

    public CardInstance drawCardInstance(
            UUID playerInstanceId,
            UUID deckInstanceId,
            UUID boardId
    ) throws GameEngineException {

        // Fetch domain objects

        PlayerInstance playerInstance = playerRepository.find(playerInstanceId);

        DeckInstance deckInstance = deckInstanceRepository.find(deckInstanceId);

        Board board = boardRepository.find(boardId);

        // Apply rules

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(playerInstance, deckInstance, board);

        playerInstanceCanDrawAnotherCardChecker.checkIfPlayerInstanceCanDrawAnotherCard(playerInstance);

        deckInstanceOwnedByPlayerInstanceChecker.checkIfDeckInstanceIsOwnedByPlayerInstance(
                deckInstance,
                playerInstance,
                board
        );

        // Draw Card and put it in hand of PlayerInstance

        CardInstance cardInstance = deckInstance.drawCard();

        playerInstance.addCardInstanceToHand(cardInstance);

        playerRepository.save(playerInstance);

        deckInstanceRepository.save(deckInstance);

        // Return Card

        return cardInstance;
    }
}
