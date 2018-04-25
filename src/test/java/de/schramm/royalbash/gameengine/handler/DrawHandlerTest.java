package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
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
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrawHandlerTest {

    private UUID playerInstanceId = UUID.randomUUID();
    private PlayerInstance playerInstance = PlayerInstance.builder()
            .id(playerInstanceId)
            .clearHandCardInstanceList()
            .build();

    private CardInstance cardInstance = mock(CardInstance.class);

    private UUID deckInstanceId = UUID.randomUUID();
    private DeckInstance deckInstance = DeckInstance.builder()
            .id(deckInstanceId)
            .cardInstance(cardInstance)
            .build();

    private UUID boardId = UUID.randomUUID();
    private Board board = Board.builder()
            .id(boardId)
            .playerBlueInstance(playerInstance)
            .playerBlueDeckInstance(deckInstance)
            .build();

    private PlayerRepository playerRepository = mock(PlayerRepository.class);

    private DeckInstanceRepository deckInstanceRepository = mock(DeckInstanceRepository.class);

    private BoardRepository boardRepository = mock(BoardRepository.class);

    {
        when(playerRepository.find(playerInstanceId)).thenReturn(playerInstance);

        when(deckInstanceRepository.find(deckInstanceId)).thenReturn(deckInstance);

        when(boardRepository.find(boardId)).thenReturn(board);
    }

    @Test
    public void draw_Card_to_empty_hand() throws GameEngineException {

        // given

        DrawHandler drawHandler = new DrawHandler(
                deckInstanceRepository,
                playerRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerInstanceCanDrawAnotherCardChecker(8),
                new DeckInstanceOwnedByPlayerInstanceChecker()
        );

        // when

        CardInstance drawnCardInstance = drawHandler.drawCardInstance(
                playerInstanceId,
                deckInstanceId,
                boardId
        );

        // then

        Assert.assertThat(drawnCardInstance, is(cardInstance));

        Assert.assertThat(playerInstance.getHandCardInstanceList(), hasItem(cardInstance));
    }

    @Test(expected = GameRuleViolationException.class)
    public void draw_Card_to_full_hand() throws GameEngineException {

        // given

        DrawHandler drawHandler = new DrawHandler(
                deckInstanceRepository,
                playerRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerInstanceCanDrawAnotherCardChecker(0),
                new DeckInstanceOwnedByPlayerInstanceChecker()
        );

        // when

        drawHandler.drawCardInstance(
                playerInstanceId,
                deckInstanceId,
                boardId
        );
    }
}
