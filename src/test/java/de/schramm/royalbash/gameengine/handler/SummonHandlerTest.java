package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.gameengine.rule.PlayerInstanceHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerInstanceOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.player.instance.PlayerInstanceRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SummonHandlerTest {

    private UUID cardInstanceId = UUID.randomUUID();
    private CardInstance cardInstance = mock(CardInstance.class);

    private UUID playerInstanceId = UUID.randomUUID();
    private PlayerInstance playerInstance = PlayerInstance.builder()
            .id(playerInstanceId)
            .handCardInstance(cardInstance)
            .build();

    private CardInstanceRepository cardInstanceRepository = mock(CardInstanceRepository.class);

    private PlayerInstanceRepository playerInstanceRepository = mock(PlayerInstanceRepository.class);

    private UUID boardId = UUID.randomUUID();

    {
        when(cardInstance.getId()).thenReturn(cardInstanceId);

        when(cardInstanceRepository.find(cardInstanceId)).thenReturn(cardInstance);

        when(playerInstanceRepository.find(playerInstanceId)).thenReturn(playerInstance);
    }

    @Test
    public void summon_Instance_existing_Board_existing_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .id(boardId)
                .playerBlueInstance(playerInstance)
                .playerRedInstance(PlayerInstance.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardInstanceRepository,
                playerInstanceRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerInstanceOnBoardChecker(),
                new PlayerInstanceHasCardInHandChecker()
        );

        // when

        Board retrievedBoard = summonHandler.summonInstance(
                boardId,
                playerInstanceId,
                cardInstanceId
        );

        // then

        Assert.assertThat(retrievedBoard.getBlueInstanceSet(), hasSize(1));

        CardInstance cardInstance = retrievedBoard.getBlueInstanceSet().iterator().next();

        Assert.assertThat(cardInstance, is(this.cardInstance));
    }

    @Test(expected = GameRuleViolationException.class)
    public void summon_Instance_existing_Board_wrong_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .id(boardId)
                .playerBlueInstance(PlayerInstance.builder().build())
                .playerRedInstance(PlayerInstance.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardInstanceRepository,
                playerInstanceRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerInstanceOnBoardChecker(),
                new PlayerInstanceHasCardInHandChecker()
        );

        // when

        summonHandler.summonInstance(
                boardId,
                playerInstanceId,
                cardInstanceId
        );
    }

    @Test(expected = GameRuleViolationException.class)
    public void summon_Instance_existing_Board_existing_PlayerInstance_existing_Card_in_empty_hand()
            throws GameEngineException {

        // given

        PlayerInstance playerInstance = PlayerInstance.builder()
                .id(playerInstanceId)
                .clearHandCardInstanceList()
                .build();

        PlayerInstanceRepository playerInstanceRepository = mock(PlayerInstanceRepository.class);
        when(playerInstanceRepository.find(playerInstanceId)).thenReturn(playerInstance);

        Board board = Board.builder()
                .id(boardId)
                .playerBlueInstance(playerInstance)
                .playerRedInstance(PlayerInstance.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardInstanceRepository,
                playerInstanceRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerInstanceOnBoardChecker(),
                new PlayerInstanceHasCardInHandChecker()
        );

        // when

        summonHandler.summonInstance(
                boardId,
                playerInstanceId,
                cardInstanceId
        );
    }
}
