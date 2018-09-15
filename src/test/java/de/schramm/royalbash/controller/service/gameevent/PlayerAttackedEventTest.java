package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Spot;
import de.schramm.royalbash.controller.service.core.creature.CreatureMock;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerAttackedEventTest {


    @Test
    public void should_invoke_attack_on_player() {

        // Given
        val creature = CreatureMock.builder()
                .id("Id 1")
                .attack(2)
                .build();
        val game = Game.builder()
                .player1(Player.builder()
                        .id("Id 2")
                        .spot(Spot.builder()
                                .creature(creature)
                                .build())
                        .build())
                .player2(Player.builder()
                        .hitpoints(5)
                        .build())
                .build();
        val testee = PlayerAttackedEvent.builder()
                .creatureId("Id 1")
                .ownerId("Id 2")
                .build();

        // When
        val updatedGame = testee.invoke(game);

        // Then
        assertThat(updatedGame.getPlayer2().getHitpoints()).isEqualTo(3);
    }
}