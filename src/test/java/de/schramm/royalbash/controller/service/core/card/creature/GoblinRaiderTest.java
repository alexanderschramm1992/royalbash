package de.schramm.royalbash.controller.service.core.card.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import de.schramm.royalbash.controller.service.core.Spot;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GoblinRaiderTest {

    @Test
    public void should_be_placeable_on_spot() {

        // Given
        val testee = GoblinRaider.builder().build();

        // When
        val isPlaceableOnSpot = testee.isPlaceableOnSpot();

        // Then
        assertThat(isPlaceableOnSpot).isTrue();
    }

    @Test
    public void should_deliver_hitpoints() {

        // Given
        val testee = GoblinRaider.builder()
                .hitpoints(2)
                .build();

        // When
        val hitpoints = testee.getHitpoints();

        // Then
        assertThat(hitpoints).isEqualTo(2);
    }

    @Test
    public void should_deliver_attack() {

        // Given
        val testee = GoblinRaider.builder()
                .attack(1)
                .build();

        // When
        val attack = testee.getAttack();

        // Then
        assertThat(attack).isEqualTo(1);
    }

    @Test
    public void should_discard_a_card_of_traget_player_when_placed_on_spot() {

        // Given
        val testee = GoblinRaider.builder().build();
        val spot = Spot.builder().build();
        val player1 = Player.builder()
                .spot(spot)
                .build();
        val player2 = Player.builder()
                .handcard(NoOpCard.builder().build())
                .handcard(NoOpCard.builder().build())
                .build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .owner(player1)
                .targetPlayer(player2)
                .targetSpot(spot)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        Assertions.assertThat(updatedGame.getPlayer2().getHandcards().collect(Collectors.toList())).hasSize(1);
    }

}