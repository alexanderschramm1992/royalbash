package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.CardRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SummonHandlerTest {

    private UUID cardId = UUID.randomUUID();
    private Card card = Card.builder()
            .id(cardId)
            .build();

    private UUID playerId = UUID.randomUUID();
    private Player player = Player.builder()
            .id(playerId)
            .card(card)
            .build();

    private CardRepository cardRepository = mock(CardRepository.class);

    private PlayerRepository playerRepository = mock(PlayerRepository.class);

    private UUID boardId = UUID.randomUUID();

    {
        when(cardRepository.find(cardId)).thenReturn(card);
        when(playerRepository.find(playerId)).thenReturn(player);
    }

    @Test
    public void summon_Instance_existing_Board_existing_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .id(boardId)
                .playerBlue(player)
                .playerRed(Player.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardRepository,
                playerRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        Board retrievedBoard = summonHandler.summon(
                boardId,
                playerId,
                cardId
        );

        // then

        Assert.assertThat(retrievedBoard.getPlayerBlue().getSummonings(), hasSize(1));
        Summoning summoning = retrievedBoard.getPlayerBlue().getSummonings().iterator().next();
        Assert.assertThat(summoning.getCard(), is(this.card));
    }

    @Test(expected = GameRuleViolationException.class)
    public void summon_Instance_existing_Board_wrong_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .id(boardId)
                .playerBlue(Player.builder().build())
                .playerRed(Player.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardRepository,
                playerRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        summonHandler.summon(
                boardId,
                playerId,
                cardId
        );
    }

    @Test(expected = GameRuleViolationException.class)
    public void summon_Instance_existing_Board_existing_PlayerInstance_existing_Card_in_empty_hand()
            throws GameEngineException {

        // given

        Player player = Player.builder()
                .id(playerId)
                .build();

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        when(playerRepository.find(playerId)).thenReturn(player);

        Board board = Board.builder()
                .id(boardId)
                .playerBlue(player)
                .playerRed(Player.builder().build())
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        SummonHandler summonHandler = new SummonHandler(
                cardRepository,
                playerRepository,
                boardRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        summonHandler.summon(
                boardId,
                playerId,
                cardId
        );
    }
}
