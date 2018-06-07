package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerTest {

    @Test
    public void playSummoningCard_shouldCreateNewSummoningOnField() throws GameEngineException {

        // Given
        val summoningCard = new TestSummoningCard(UUID.randomUUID());
        val target = Target.builder().build();
        val summoningId = UUID.randomUUID();

        val player = Player.builder()
                .resourcePool(ResourcePool.builder().build())
                .hand(Hand.builder().card(summoningCard).build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, summoningId);

        // Then
        val summoningsOfPlayer = player
                .getField()
                .getTargets()
                .stream()
                .map(Target::getSummoning)
                .collect(Collectors.toSet());
        Assert.assertThat(summoningsOfPlayer, Matchers.hasItem(Summoning.fromCard(summoningCard, summoningId)));
    }

    @Test
    public void playSummoningCard_shouldRemoveCardFromHand() throws GameEngineException {

        // Given
        val summoningCard = new TestSummoningCard(UUID.randomUUID());
        val target = Target.builder().build();

        val player = Player.builder()
                .resourcePool(ResourcePool.builder().build())
                .hand(Hand.builder().card(summoningCard).build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, UUID.randomUUID());

        // Then
        Assert.assertThat(player.getHand().getCards(), Matchers.not(Matchers.hasItem(summoningCard)));
    }

    @Test
    public void playSummoningCard_shouldReduceResourcePool() throws GameEngineException {

        // Given
        val initialRations = 6;
        val initialMaterial = 5;
        val initialBlessing = 4;
        val resourcePool = ResourcePool.builder()
                .rations(initialRations)
                .material(initialMaterial)
                .blessing(initialBlessing)
                .build();
        val summoningCard = new TestSummoningCard(UUID.randomUUID(), 2, 3, 4);
        val target = Target.builder().build();

        val player = Player.builder()
                .resourcePool(resourcePool)
                .hand(Hand.builder().card(summoningCard).build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, UUID.randomUUID());

        // Then
        Assert.assertThat(
                resourcePool.getRations(),
                Matchers.is(initialRations - summoningCard.getCostRations())
        );
        Assert.assertThat(
                resourcePool.getMaterial(),
                Matchers.is(initialMaterial - summoningCard.getCostMaterial())
        );
        Assert.assertThat(
                resourcePool.getBlessing(),
                Matchers.is(initialBlessing - summoningCard.getCostBlessing())
        );
    }

    @Test(expected = RuleViolationException.class)
    public void playSummoningCard_shouldThrowException_whenSummoningCardNotInHand() throws GameEngineException {

        // Given
        val summoningCard = new TestSummoningCard(UUID.randomUUID());
        val target = Target.builder().build();

        val player = Player.builder()
                .resourcePool(ResourcePool.builder().build())
                .hand(Hand.builder().build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, UUID.randomUUID());
    }

    @Test(expected = RuleViolationException.class)
    public void playSummoningCard_shouldThrowException_whenTargetIsOccupied() throws GameEngineException {

        // Given
        val summoningCard = new TestSummoningCard(UUID.randomUUID());
        val target = Target.builder().summoning(Summoning.fromCard(summoningCard, UUID.randomUUID())).build();

        val player = Player.builder()
                .resourcePool(ResourcePool.builder().build())
                .hand(Hand.builder().card(summoningCard).build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, UUID.randomUUID());
    }

    @Test(expected = RuleViolationException.class)
    public void playSummoningCard_shouldThrowException_whenNotEnoughResources() throws GameEngineException {

        // Given
        val summoningCard = new TestSummoningCard(UUID.randomUUID(), 1, 1, 1);
        val target = Target.builder().summoning(Summoning.fromCard(summoningCard, UUID.randomUUID())).build();

        val player = Player.builder()
                .resourcePool(ResourcePool.builder().build())
                .hand(Hand.builder().card(summoningCard).build())
                .field(Field.builder().target(target).build())
                .build();

        // When
        player.playSummoningCard(summoningCard, target, UUID.randomUUID());
    }
}
