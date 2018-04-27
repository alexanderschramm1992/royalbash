package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.gameengine.rule.PlayerCanDrawAnotherCardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrawHandlerTest {

    private Card card = Card.builder().build();

    private UUID deckId = UUID.randomUUID();
    private Deck deck = Deck.builder()
            .id(deckId)
            .card(card)
            .build();

    private UUID playerId = UUID.randomUUID();
    private Player player = Player.builder()
            .id(playerId)
            .deck(deck)
            .build();

    private UUID boardId = UUID.randomUUID();
    private Board board = Board.builder()
            .id(boardId)
            .playerBlue(player)
            .build();

    private PlayerRepository playerRepository = mock(PlayerRepository.class);
    private DeckRepository deckRepository = mock(DeckRepository.class);
    private BoardRepository boardRepository = mock(BoardRepository.class);

    {
        when(playerRepository.find(playerId)).thenReturn(player);
        when(deckRepository.find(deckId)).thenReturn(deck);
        when(boardRepository.find(boardId)).thenReturn(board);
    }

    @Test
    public void draw_Card_to_empty_hand() throws GameEngineException {

        // given

        DrawHandler drawHandler = new DrawHandler(
                playerRepository,
                new RequiredDomainObjectChecker(),
                new PlayerCanDrawAnotherCardChecker(8)
        );

        // when

        Card drawnCard = drawHandler.drawCard(
                playerId
        );

        // then

        Assert.assertThat(drawnCard, is(card));

        Assert.assertThat(player.getCards(), hasItem(card));
    }

    @Test(expected = GameRuleViolationException.class)
    public void draw_Card_to_full_hand() throws GameEngineException {

        // given

        DrawHandler drawHandler = new DrawHandler(
                playerRepository,
                new RequiredDomainObjectChecker(),
                new PlayerCanDrawAnotherCardChecker(0)
        );

        // when

        drawHandler.drawCard(
                playerId
        );
    }
}
