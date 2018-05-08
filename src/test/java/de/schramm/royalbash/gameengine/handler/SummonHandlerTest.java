package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.card.CardRepository;
import de.schramm.royalbash.persistence.game.GameRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import de.schramm.royalbash.persistence.target.TargetRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
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

    private UUID targetId = UUID.randomUUID();
    private Target target = Target.builder()
            .id(targetId)
            .build();

    private CardRepository cardRepository = mock(CardRepository.class);

    private PlayerRepository playerRepository = mock(PlayerRepository.class);

    private TargetRepository targetRepository = mock(TargetRepository.class);

    private UUID gameId = UUID.randomUUID();

    {
        when(cardRepository.find(cardId)).thenReturn(card);
        when(playerRepository.find(playerId)).thenReturn(player);
        when(targetRepository.find(targetId)).thenReturn(target);
    }

    @Test
    public void summon_Instance_existing_Board_existing_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .playerBlue(player)
                .playerRed(Player.builder().build())
                .build();

        Game game = Game.builder()
                .board(board)
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        SummonHandler summonHandler = new SummonHandler(
                gameRepository,
                cardRepository,
                playerRepository,
                targetRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        Game result = summonHandler.summon(
                gameId,
                playerId,
                cardId,
                targetId
        );

        // then

        Assert.assertThat(
                result.getBoard().getPlayerBlue().getTargets().iterator().next().getSummoning().getCard(),
                is(this.card)
        );
    }

    @Test(expected = GameRuleViolationException.class)
    public void summon_Instance_existing_Board_wrong_PlayerInstance_existing_Card_in_hand()
            throws GameEngineException {

        // given

        Board board = Board.builder()
                .id(gameId)
                .playerBlue(Player.builder().build())
                .playerRed(Player.builder().build())
                .build();

        Game game = Game.builder()
                .board(board)
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        SummonHandler summonHandler = new SummonHandler(
                gameRepository,
                cardRepository,
                playerRepository,
                targetRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        summonHandler.summon(
                gameId,
                playerId,
                cardId,
                targetId
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
                .id(gameId)
                .playerBlue(player)
                .playerRed(Player.builder().build())
                .build();

        Game game = Game.builder()
                .board(board)
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        SummonHandler summonHandler = new SummonHandler(
                gameRepository,
                cardRepository,
                playerRepository,
                targetRepository,
                new RequiredDomainObjectChecker(),
                new PlayerOnBoardChecker(),
                new PlayerHasCardInHandChecker()
        );

        // when

        summonHandler.summon(
                gameId,
                playerId,
                cardId,
                targetId
        );
    }
}
