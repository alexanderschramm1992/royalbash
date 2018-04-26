package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SumoningCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.summoning.SummoningRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackHandlerTest {

    private final UUID playerBlueId = UUID.randomUUID();
    private final Player playerBlue = Player.builder()
            .id(playerBlueId)
            .health(2)
            .build();

    private final UUID playerRedId = UUID.randomUUID();
    private final Player playerRed = Player.builder()
            .id(playerRedId)
            .build();

    private final PlayerRepository playerRepository = mock(PlayerRepository.class);

    private final Turn turn = Turn.builder()
            .player(playerBlue)
            .counter(0)
            .build();

    private final UUID card1Id = UUID.randomUUID();
    private final Card card1 = Card.builder()
            .id(card1Id)
            .name("Card 1")
            .cost(2)
            .health(2)
            .strength(2)
            .build();

    private final UUID summoning1Id = UUID.randomUUID();
    private final Summoning summoning1 = Summoning.builder()
            .id(summoning1Id)
            .card(card1)
            .currentCost(2)
            .currentHealth(2)
            .currentStrength(2)
            .build();

    private final UUID card2Id = UUID.randomUUID();
    private final Card card2 = Card.builder()
            .id(card2Id)
            .name("Card 2")
            .cost(1)
            .health(1)
            .strength(2)
            .build();

    private final UUID summoning2Id = UUID.randomUUID();
    private final Summoning summoning2 = Summoning.builder()
            .id(summoning2Id)
            .card(card2)
            .currentCost(1)
            .currentHealth(1)
            .currentStrength(1)
            .build();

    private final UUID card3Id = UUID.randomUUID();
    private final Card card3 = Card.builder()
            .id(card3Id)
            .name("Card 3")
            .cost(1)
            .health(1)
            .strength(1)
            .build();

    private final UUID summoning3Id = UUID.randomUUID();
    private final Summoning summoning3 = Summoning.builder()
            .id(summoning3Id)
            .card(card3)
            .currentCost(1)
            .currentHealth(1)
            .currentStrength(1)
            .build();

    private final UUID boardId = UUID.randomUUID();
    private final SummoningRepository summoningRepository = mock(SummoningRepository.class);

    {
        when(playerRepository.find(playerBlueId)).thenReturn(playerBlue);
        when(playerRepository.find(playerRedId)).thenReturn(playerRed);

        when(summoningRepository.find(summoning1Id)).thenReturn(summoning1);
        when(summoningRepository.find(summoning2Id)).thenReturn(summoning2);
        when(summoningRepository.find(summoning3Id)).thenReturn(summoning3);
    }

    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_defender() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .summoning(summoning1)
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .summoning(summoning2)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlue(playerBlue)
                        .playerRed(playerRed)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                summoning1Id,
                summoning2Id
        );

        // then

        Assert.assertThat(board.getPlayerBlue().getSummonings(), hasSize(1));
        Assert.assertThat(board.getPlayerRed().getSummonings(), hasSize(0));
        Summoning summoningSet = board.getPlayerBlue().getSummonings().iterator().next();
        Assert.assertThat(summoningSet, is(summoning1));
        Assert.assertThat(summoning1.getCurrentHealth(), is(1));
    }


    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_attacker() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .summoning(summoning2)
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .summoning(summoning1)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlue(playerBlue)
                        .playerRed(playerRed)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                summoning2Id,
                summoning1Id
        );

        // then

        Assert.assertThat(board.getPlayerBlue().getSummonings(), hasSize(0));
        Assert.assertThat(board.getPlayerRed().getSummonings(), hasSize(1));
        Summoning summoningSet = board.getPlayerRed().getSummonings().iterator().next();
        Assert.assertThat(summoningSet, is(summoning1));
        Assert.assertThat(summoning1.getCurrentHealth(), is(1));
    }

    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_both() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .summoning(summoning2)
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .summoning(summoning3)
                .build();

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlue(playerBlue)
                        .playerRed(playerRed)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                summoning2Id,
                summoning3Id
        );

        // then

        Assert.assertThat(board.getPlayerBlue().getSummonings(), hasSize(0));
        Assert.assertThat(board.getPlayerRed().getSummonings(), hasSize(0));
    }
}
