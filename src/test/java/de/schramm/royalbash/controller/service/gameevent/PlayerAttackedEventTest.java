package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Spot;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerAttackedEventTest {


    @Test
    public void should_invoke_attack_on_player() {

        // Given
        val creature = mock(Creature.class);
        when(creature.getId()).thenReturn("Id 1");
        when(creature.getAttack()).thenReturn(2);
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