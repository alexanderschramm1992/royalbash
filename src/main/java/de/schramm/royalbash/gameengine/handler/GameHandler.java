package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.BlueprintOwnedByAccountChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.model.Blueprint;
import de.schramm.royalbash.model.Deck;
import de.schramm.royalbash.persistence.account.AccountRepository;
import de.schramm.royalbash.persistence.blueprint.BlueprintRepository;
import de.schramm.royalbash.persistence.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GameHandler {

    // Repositories
    private final GameRepository gameRepository;
    private final AccountRepository accountRepository;
    private final BlueprintRepository blueprintRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final BlueprintOwnedByAccountChecker blueprintOwnedByAccountChecker;

    @Autowired
    public GameHandler(
            GameRepository gameRepository,
            AccountRepository accountRepository,
            BlueprintRepository blueprintRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            BlueprintOwnedByAccountChecker blueprintOwnedByAccountChecker
    ) {
        this.gameRepository = gameRepository;
        this.accountRepository = accountRepository;
        this.blueprintRepository = blueprintRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.blueprintOwnedByAccountChecker = blueprintOwnedByAccountChecker;
    }

    public Game getGame(
            UUID gameId
    ) throws DomainObjectDoesNotExistException {

        // Fetch domain objects

        Game game = gameRepository.find(gameId);

        // Apply rules

        requiredDomainObjectChecker.check(game);

        // Return game

        return game;
    }

    public Game startGame(
            UUID accountBlueId,
            UUID accountRedId,
            UUID blueBlueprintId,
            UUID redBlueprintId
    ) throws GameEngineException {

        // Fetch domain objects

        Account accountBlue = accountRepository.find(accountBlueId);
        Account accountRed = accountRepository.find(accountRedId);
        Blueprint blueprintBlue = blueprintRepository.find(blueBlueprintId);
        Blueprint blueprintRed = blueprintRepository.find(redBlueprintId);

        // Apply rules

        requiredDomainObjectChecker.check(
                accountBlue,
                accountRed,
                blueprintBlue,
                blueprintRed
        );
        blueprintOwnedByAccountChecker.check(
                blueprintBlue,
                accountBlue
        );
        blueprintOwnedByAccountChecker.check(
                blueprintRed,
                accountRed
        );

        // Save new domain objects

        Deck deckBlue = Deck.fromBlueprint(blueprintBlue, UUID.randomUUID());
        Deck deckRed = Deck.fromBlueprint(blueprintRed, UUID.randomUUID());

        UUID playerBlueId = UUID.randomUUID();

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .accountId(accountBlue.getId())
                .clearCards()
                .deck(deckBlue)
                .build();
        Player playerRed = Player.builder()
                .id(UUID.randomUUID())
                .accountId(accountRed.getId())
                .clearCards()
                .deck(deckRed)
                .build();

        Turn turn = Turn.builder()
                .counter(0)
                .playerId(playerBlueId)
                .build();

        Board board = Board.builder()
                .id(UUID.randomUUID())
                .playerBlue(playerBlue)
                .playerRed(playerRed)
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

        requiredDomainObjectChecker.check(game);

        // Delete Game

        gameRepository.delete(game.getId());
    }
}
