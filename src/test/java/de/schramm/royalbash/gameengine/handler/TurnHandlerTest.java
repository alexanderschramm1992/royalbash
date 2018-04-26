package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.persistence.board.BoardRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TurnHandlerTest {

    @Test
    public void get_existing_Turn() throws DomainObjectDoesNotExistException {

        // given

        Turn turn = Turn.builder().build();

        UUID boardId = UUID.randomUUID();
        Board board = Board.builder()
                .id(boardId)
                .turn(turn)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        TurnHandler turnHandler = new TurnHandler(
                boardRepository,
                new RequiredDomainObjectChecker()
        );

        // when

        Turn retrievedTurn = turnHandler.getTurn(boardId);

        // then

        Assert.assertThat(retrievedTurn, is(turn));
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void get_non_existing_Turn() throws DomainObjectDoesNotExistException {

        // given

        UUID boardId = UUID.randomUUID();
        Board board = Board.builder()
                .id(boardId)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        TurnHandler turnHandler = new TurnHandler(
                boardRepository,
                new RequiredDomainObjectChecker()
        );

        // when

        turnHandler.getTurn(boardId);
    }

    @Test
    public void end_existing_Turn() throws DomainObjectDoesNotExistException {

        // given

        UUID playerInstanceId1 = UUID.randomUUID();
        Player player1 = Player.builder()
                .id(playerInstanceId1)
                .build();

        UUID playerInstanceId2 = UUID.randomUUID();
        Player player2 = Player.builder()
                .id(playerInstanceId2)
                .build();

        Turn turn = Turn.builder()
                .counter(0)
                .player(player1)
                .build();

        UUID boardId = UUID.randomUUID();
        Board board = Board.builder()
                .id(boardId)
                .turn(turn)
                .playerBlue(player1)
                .playerRed(player2)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        TurnHandler turnHandler = new TurnHandler(
                boardRepository,
                new RequiredDomainObjectChecker()
        );

        // when

        turnHandler.endTurn(boardId);

        // then

        Assert.assertThat(turn.getCounter(), is(1));

        Assert.assertThat(turn.getPlayer(), is(player2));
    }

    @Test(expected = DomainObjectDoesNotExistException.class)
    public void end_non_existing_Turn() throws DomainObjectDoesNotExistException {

        // given

        UUID boardId = UUID.randomUUID();
        Board board = Board.builder()
                .id(boardId)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(board);

        TurnHandler turnHandler = new TurnHandler(
                boardRepository,
                new RequiredDomainObjectChecker()
        );

        // when

        turnHandler.endTurn(boardId);
    }
}
