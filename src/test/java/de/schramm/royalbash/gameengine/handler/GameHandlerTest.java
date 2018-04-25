package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.DeckOwnedByPlayerChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Account;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.game.GameRepository;
import de.schramm.royalbash.persistence.account.AccountRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameHandlerTest {

    private UUID gameId = UUID.randomUUID();

    private UUID playerBlueDeckId = UUID.randomUUID();
    private Deck playerBlueDeck = Deck.builder()
            .id(playerBlueDeckId)
            .clearCardList()
            .build();

    private UUID playerRedDeckId = UUID.randomUUID();
    private Deck playerRedDeck = Deck.builder()
            .id(playerRedDeckId)
            .clearCardList()
            .build();

    private UUID playerBlueId = UUID.randomUUID();
    private Account accountBlue = Account.builder()
            .id(playerBlueId)
            .name("TestBlue")
            .email("")
            .passwordHash("")
            .deck(playerBlueDeck)
            .build();

    private UUID playerRedId = UUID.randomUUID();
    private Account accountRed = Account.builder()
            .id(playerRedId)
            .name("TestRed")
            .email("")
            .passwordHash("")
            .deck(playerRedDeck)
            .build();

    private DeckRepository deckRepository = mock(DeckRepository.class);

    private RequiredDomainObjectChecker requiredDomainObjectChecker = new RequiredDomainObjectChecker();

    private DeckOwnedByPlayerChecker deckOwnedByPlayerChecker = new DeckOwnedByPlayerChecker();

    {
        when(deckRepository.find(playerBlueDeckId)).thenReturn(playerBlueDeck);
        when(deckRepository.find(playerRedDeckId)).thenReturn(playerRedDeck);
    }

    @Test
    public void start_Game_with_existing_two_existing_Players() throws GameEngineException {

        // given

        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.find(playerBlueId)).thenReturn(accountBlue);
        when(accountRepository.find(playerRedId)).thenReturn(accountRed);

        GameRepository gameRepository = mock(GameRepository.class);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                accountRepository,
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // when

        Game game = gameHandler.startGame(
                playerBlueId,
                playerRedId,
                playerBlueDeckId,
                playerRedDeckId
        );

        // then

        Assert.assertThat(game.getAccountBlue(), is(accountBlue));

        Assert.assertThat(game.getAccountRed(), is(accountRed));
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void start_Game_with_one_existing_Player() throws GameEngineException {

        // given

        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.find(playerBlueId)).thenReturn(accountBlue);
        when(accountRepository.find(playerRedId)).thenReturn(null);

        GameRepository gameRepository = mock(GameRepository.class);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                accountRepository,
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // when

        gameHandler.startGame(
                playerBlueId,
                playerRedId,
                playerBlueDeckId,
                playerRedDeckId
        );
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void start_Game_with_no_existing_Players() throws GameEngineException {

        // given

        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.find(any())).thenReturn(null);

        GameRepository gameRepository = mock(GameRepository.class);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                accountRepository,
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // when

        gameHandler.startGame(
                playerBlueId,
                playerRedId,
                playerBlueDeckId,
                playerRedDeckId
        );
    }

    @Test
    public void end_existing_Game() throws GameEngineException {

        // given

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(
                Game.builder()
                        .id(gameId)
                        .build()
        );

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                mock(AccountRepository.class),
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // When

        gameHandler.endGame(gameId);
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void end_non_existing_Game() throws GameEngineException {

        // given

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(any())).thenReturn(null);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                mock(AccountRepository.class),
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // When

        gameHandler.endGame(gameId);
    }

    @Test
    public void get_existing_Game() throws GameEngineException {

        // given

        Game game = Game.builder()
                .id(gameId)
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                mock(AccountRepository.class),
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // when

        Game gameFound = gameHandler.getGame(gameId);

        // then

        Assert.assertThat(gameFound, is(game));
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void get_non_existing_Game() throws GameEngineException {

        // given

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(any())).thenReturn(null);

        GameHandler gameHandler = new GameHandler(
                gameRepository,
                mock(AccountRepository.class),
                deckRepository,
                requiredDomainObjectChecker,
                deckOwnedByPlayerChecker
        );

        // when

        gameHandler.getGame(gameId);
    }
}
