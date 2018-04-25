package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.DeckOwnedByPlayerChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.player.Account;
import de.schramm.royalbash.model.Turn;
import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.deck.DeckInstance;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.game.GameRepository;
import de.schramm.royalbash.persistence.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GameHandler {

    // Repositories
    private final GameRepository gameRepository;
    private final AccountRepository accountRepository;
    private final DeckRepository deckRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final DeckOwnedByPlayerChecker deckOwnedByPlayerChecker;

    @Autowired
    public GameHandler(
            GameRepository gameRepository,
            AccountRepository accountRepository,
            DeckRepository deckRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            DeckOwnedByPlayerChecker deckOwnedByPlayerChecker
    ) {
        this.gameRepository = gameRepository;
        this.accountRepository = accountRepository;
        this.deckRepository = deckRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.deckOwnedByPlayerChecker = deckOwnedByPlayerChecker;
    }

    public Game getGame(
            UUID gameId
    ) throws DomainObjectDoesNotExistException {

        // Fetch domain objects

        Game game = gameRepository.find(gameId);

        // Apply rules

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(game);

        // Return game

        return game;
    }

    public Game startGame(
            UUID playerBlueId,
            UUID playerRedId,
            UUID playerBlueDeckId,
            UUID playerRedDeckId
    ) throws GameEngineException {

        // Fetch domain objects

        Account accountBlue = accountRepository.find(playerBlueId);

        Account accountRed = accountRepository.find(playerRedId);

        Deck playerBlueDeck = deckRepository.find(playerBlueDeckId);

        Deck playerRedDeck = deckRepository.find(playerRedDeckId);

        // Apply rules

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(
                accountBlue,
                accountRed,
                playerBlueDeck,
                playerRedDeck
        );

        deckOwnedByPlayerChecker.checkIfDeckIsOwnedByPlayer(
                playerBlueDeck,
                accountBlue
        );

        deckOwnedByPlayerChecker.checkIfDeckIsOwnedByPlayer(
                playerRedDeck,
                accountRed
        );

        // Save new domain objects

        Turn turn = Turn.builder()
                .turnCounter(0)
                .playerInstanceId(playerBlueId)
                .build();

        List<CardInstance> playerBlueDeckInstanceCardInstanceList = playerBlueDeck.getCardList().stream()
                .map(Card::toInstance)
                .collect(Collectors.toList());

        DeckInstance playerBlueDeckInstance = DeckInstance.builder()
                .id(UUID.randomUUID())
                .cardInstanceList(playerBlueDeckInstanceCardInstanceList)
                .build();

        List<CardInstance> playerRedDeckInstanceCardInstanceList = playerRedDeck.getCardList().stream()
                .map(Card::toInstance)
                .collect(Collectors.toList());

        DeckInstance playerRedDeckInstance = DeckInstance.builder()
                .id(UUID.randomUUID())
                .cardInstanceList(playerRedDeckInstanceCardInstanceList)
                .build();

        Board board = Board.builder()
                .id(UUID.randomUUID())
                .clearBlueInstanceSet()
                .clearRedInstanceSet()
                .playerBlueDeckInstance(playerBlueDeckInstance)
                .playerRedDeckInstance(playerRedDeckInstance)
                .turn(turn)
                .build();

        Game game = Game.builder()
                .id(UUID.randomUUID())
                .accountBlue(accountBlue)
                .accountRed(accountRed)
                .board(board)
                .build();

        gameRepository.save(game);

        // Return game

        return game;
    }

    public void endGame(
            UUID gameId
    ) throws DomainObjectDoesNotExistException {

        // Fetch domain objects

        Game game = gameRepository.find(gameId);

        // Apply rule

        requiredDomainObjectChecker.checkIfRequiredDomainObjectsExist(game);

        // Delete Game

        gameRepository.delete(game.getId());
    }
}
