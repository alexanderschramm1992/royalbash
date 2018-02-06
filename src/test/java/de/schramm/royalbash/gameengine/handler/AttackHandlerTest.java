package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.InstanceCanAttackBeAttackedChecker;
import de.schramm.royalbash.gameengine.rule.InstanceOwnedByPlayerInstanceOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.InstancesOwnedByOpposingPlayerInstancesChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.card.instance.CreatureInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.player.instance.PlayerInstanceRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackHandlerTest {

    private final UUID playerBlueInstanceId = UUID.randomUUID();
    private final UUID playerRedInstanceId = UUID.randomUUID();
    private final PlayerInstance playerBlueInstance = PlayerInstance.builder()
            .id(playerBlueInstanceId)
            .currentHealth(2)
            .build();
    private final PlayerInstance playerRedInstance = PlayerInstance.builder()
            .id(playerRedInstanceId)
            .build();
    private final PlayerInstanceRepository playerInstanceRepository = mock(PlayerInstanceRepository.class);
    private final Turn turn = Turn.builder()
            .playerInstanceId(playerBlueInstanceId)
            .turnCounter(0)
            .build();
    private final UUID creatureInstanceId1 = UUID.randomUUID();
    private final CreatureInstance creatureInstance1 = CreatureInstance.builder()
            .id(creatureInstanceId1)
            .name("Creature 1")
            .cost(2)
            .currentCost(2)
            .health(2)
            .currentHealth(2)
            .strength(2)
            .currentStrength(2)
            .clearEquippedSet()
            .build();
    private final UUID creatureInstanceId2 = UUID.randomUUID();
    private final CreatureInstance creatureInstance2 = CreatureInstance.builder()
            .id(creatureInstanceId2)
            .name("Creature 2")
            .cost(1)
            .currentCost(1)
            .health(1)
            .currentHealth(1)
            .strength(1)
            .currentStrength(1)
            .clearEquippedSet()
            .build();
    private final UUID creatureInstanceId3 = UUID.randomUUID();
    private final CreatureInstance creatureInstance3 = CreatureInstance.builder()
            .id(creatureInstanceId3)
            .name("Creature 3")
            .cost(1)
            .currentCost(1)
            .health(1)
            .currentHealth(1)
            .strength(1)
            .currentStrength(1)
            .clearEquippedSet()
            .build();
    private final UUID boardId = UUID.randomUUID();
    private final CardInstanceRepository cardInstanceRepository = mock(CardInstanceRepository.class);

    {
        when(playerInstanceRepository.find(playerBlueInstanceId)).thenReturn(playerBlueInstance);
        when(playerInstanceRepository.find(playerRedInstanceId)).thenReturn(playerRedInstance);

        when(cardInstanceRepository.find(creatureInstanceId1)).thenReturn(creatureInstance1);
        when(cardInstanceRepository.find(creatureInstanceId2)).thenReturn(creatureInstance2);
        when(cardInstanceRepository.find(creatureInstanceId3)).thenReturn(creatureInstance3);
    }

    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_defender() throws GameEngineException {

        // given

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlueInstance(playerBlueInstance)
                        .playerRedInstance(playerRedInstance)
                        .blueInstance(creatureInstance1)
                        .redInstance(creatureInstance2)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                cardInstanceRepository,
                new RequiredDomainObjectChecker(),
                new InstanceCanAttackBeAttackedChecker(),
                new InstanceOwnedByPlayerInstanceOnBoardChecker(),
                new InstancesOwnedByOpposingPlayerInstancesChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                creatureInstanceId1,
                creatureInstanceId2
        );

        // then

        Assert.assertThat(board.getBlueInstanceSet(), hasSize(1));

        Assert.assertThat(board.getRedInstanceSet(), hasSize(0));

        CardInstance cardInstanceFromBlueInstanceSet = board.getBlueInstanceSet().iterator().next();

        Assert.assertThat(cardInstanceFromBlueInstanceSet, is(creatureInstance1));

        Assert.assertThat(cardInstanceFromBlueInstanceSet, instanceOf(CreatureInstance.class));

        CreatureInstance castedInstanceFromBlueInstanceSet = (CreatureInstance) cardInstanceFromBlueInstanceSet;

        Assert.assertThat(castedInstanceFromBlueInstanceSet.getCurrentHealth(), is(1));
    }

    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_attacker() throws GameEngineException {

        // given

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlueInstance(playerBlueInstance)
                        .playerRedInstance(playerRedInstance)
                        .blueInstance(creatureInstance2)
                        .redInstance(creatureInstance1)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                cardInstanceRepository,
                new RequiredDomainObjectChecker(),
                new InstanceCanAttackBeAttackedChecker(),
                new InstanceOwnedByPlayerInstanceOnBoardChecker(),
                new InstancesOwnedByOpposingPlayerInstancesChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                creatureInstanceId2,
                creatureInstanceId1
        );

        // then

        Assert.assertThat(board.getBlueInstanceSet(), hasSize(0));

        Assert.assertThat(board.getRedInstanceSet(), hasSize(1));

        CardInstance cardInstanceFromRedInstanceSet = board.getRedInstanceSet().iterator().next();

        Assert.assertThat(cardInstanceFromRedInstanceSet, is(creatureInstance1));

        Assert.assertThat(cardInstanceFromRedInstanceSet, instanceOf(CreatureInstance.class));

        CreatureInstance castedInstanceFromRedInstanceSet = (CreatureInstance) cardInstanceFromRedInstanceSet;

        Assert.assertThat(castedInstanceFromRedInstanceSet.getCurrentHealth(), is(1));
    }

    @Test
    public void attack_Instance_Creature_to_Creature_lethal_for_both() throws GameEngineException {

        // given

        BoardRepository boardRepository = mock(BoardRepository.class);
        when(boardRepository.find(boardId)).thenReturn(
                Board.builder()
                        .id(boardId)
                        .turn(turn)
                        .playerBlueInstance(playerBlueInstance)
                        .playerRedInstance(playerRedInstance)
                        .blueInstance(creatureInstance2)
                        .redInstance(creatureInstance3)
                        .build()
        );

        AttackHandler attackHandler = new AttackHandler(
                boardRepository,
                cardInstanceRepository,
                new RequiredDomainObjectChecker(),
                new InstanceCanAttackBeAttackedChecker(),
                new InstanceOwnedByPlayerInstanceOnBoardChecker(),
                new InstancesOwnedByOpposingPlayerInstancesChecker()
        );

        // when

        Board board = attackHandler.attackCardInstance(
                boardId,
                creatureInstanceId2,
                creatureInstanceId3
        );

        // then

        Assert.assertThat(board.getBlueInstanceSet(), hasSize(0));

        Assert.assertThat(board.getRedInstanceSet(), hasSize(0));
    }
}
