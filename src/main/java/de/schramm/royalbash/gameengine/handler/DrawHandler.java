package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.DeckOwnedByPlayerChecker;
import de.schramm.royalbash.gameengine.rule.PlayerCanDrawAnotherCardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Deck;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DrawHandler {

    // Repositories
    private final DeckRepository deckRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker;
    private final DeckOwnedByPlayerChecker deckOwnedByPlayerChecker;

    @Autowired
    public DrawHandler(
            DeckRepository deckRepository,
            PlayerRepository playerRepository,
            BoardRepository boardRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker,
            DeckOwnedByPlayerChecker deckOwnedByPlayerChecker
    ) {
        this.deckRepository = deckRepository;
        this.playerRepository = playerRepository;
        this.boardRepository = boardRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerCanDrawAnotherCardChecker = playerCanDrawAnotherCardChecker;
        this.deckOwnedByPlayerChecker = deckOwnedByPlayerChecker;
    }

    public Card drawCard(
            UUID playerId,
            UUID deckId,
            UUID boardId
    ) throws GameEngineException {

        // Fetch domain objects

        Player player = playerRepository.find(playerId);
        Deck deck = deckRepository.find(deckId);
        Board board = boardRepository.find(boardId);

        // Apply rules

        requiredDomainObjectChecker.check(player, deck, board);
        playerCanDrawAnotherCardChecker.checkIfPlayerInstanceCanDrawAnotherCard(player);
        deckOwnedByPlayerChecker.check(
                deck,
                player
        );

        // Draw Card and put it in hand of Player

        Card card = deck.drawCard();
        player.addCard(card);
        playerRepository.save(player);
        deckRepository.save(deck);

        // Return Card

        return card;
    }
}
