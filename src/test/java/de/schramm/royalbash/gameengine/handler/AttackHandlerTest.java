package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.SumoningCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.SummoningOwnedByPlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.SummoningsOwnedByOpposingPlayersChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.game.GameRepository;
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

    private final UUID gameId = UUID.randomUUID();
    private final SummoningRepository summoningRepository = mock(SummoningRepository.class);

    {
        when(playerRepository.find(playerBlueId)).thenReturn(playerBlue);
        when(playerRepository.find(playerRedId)).thenReturn(playerRed);

        when(summoningRepository.find(summoning1Id)).thenReturn(summoning1);
        when(summoningRepository.find(summoning2Id)).thenReturn(summoning2);
        when(summoningRepository.find(summoning3Id)).thenReturn(summoning3);
    }

    @Test
    public void attack_lethal_for_defender() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .target(Target.builder().summoning(summoning1).build())
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .target(Target.builder().summoning(summoning2).build())
                .build();

        Game game = Game.builder()
                .id(gameId)
                .board(
                        Board.builder()
                                .id(gameId)
                                .turn(turn)
                                .playerBlue(playerBlue)
                                .playerRed(playerRed)
                                .build()
                )
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        AttackHandler attackHandler = new AttackHandler(
                gameRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Game result = attackHandler.attackSummoning(
                gameId,
                summoning1Id,
                summoning2Id
        );

        // then

        Assert.assertThat(result.getBoard().getPlayerBlue().getTargets(), hasSize(1));
        Assert.assertThat(result.getBoard().getPlayerRed().getTargets(), hasSize(0));
        Summoning summoningSet = result.getBoard().getPlayerBlue().getTargets().iterator().next().getSummoning();
        Assert.assertThat(summoningSet, is(summoning1));
        Assert.assertThat(summoning1.getCurrentHealth(), is(1));
    }


    @Test
    public void attack_lethal_for_attacker() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .target(Target.builder().summoning(summoning2).build())
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .target(Target.builder().summoning(summoning1).build())
                .build();

        Game game = Game.builder()
                .id(gameId)
                .board(
                        Board.builder()
                                .id(gameId)
                                .turn(turn)
                                .playerBlue(playerBlue)
                                .playerRed(playerRed)
                                .build()
                )
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        AttackHandler attackHandler = new AttackHandler(
                gameRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Game result = attackHandler.attackSummoning(
                gameId,
                summoning2Id,
                summoning1Id
        );

        // then

        Assert.assertThat(result.getBoard().getPlayerBlue().getTargets(), hasSize(0));
        Assert.assertThat(result.getBoard().getPlayerRed().getTargets(), hasSize(1));
        Summoning summoningSet = result.getBoard().getPlayerRed().getTargets().iterator().next().getSummoning();
        Assert.assertThat(summoningSet, is(summoning1));
        Assert.assertThat(summoning1.getCurrentHealth(), is(1));
    }

    @Test
    public void attack_lethal_for_both() throws GameEngineException {

        // given

        Player playerBlue = Player.builder()
                .id(playerBlueId)
                .target(Target.builder().summoning(summoning2).build())
                .build();

        Player playerRed = Player.builder()
                .id(playerRedId)
                .target(Target.builder().summoning(summoning3).build())
                .build();

        Game game = Game.builder()
                .id(gameId)
                .board(
                        Board.builder()
                                .id(gameId)
                                .turn(turn)
                                .playerBlue(playerBlue)
                                .playerRed(playerRed)
                                .build()
                )
                .build();

        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.find(gameId)).thenReturn(game);

        AttackHandler attackHandler = new AttackHandler(
                gameRepository,
                summoningRepository,
                new RequiredDomainObjectChecker(),
                new SumoningCanAttackBeAttackedChecker(),
                new SummoningOwnedByPlayerOnBoardChecker(),
                new SummoningsOwnedByOpposingPlayersChecker()
        );

        // when

        Game result = attackHandler.attackSummoning(
                gameId,
                summoning2Id,
                summoning3Id
        );

        // then

        Assert.assertThat(result.getBoard().getPlayerBlue().getTargets(), hasSize(0));
        Assert.assertThat(result.getBoard().getPlayerRed().getTargets(), hasSize(0));
    }
}
