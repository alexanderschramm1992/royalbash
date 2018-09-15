package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Spot;
import de.schramm.royalbash.controller.service.core.creature.CreatureMock;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatureAttackedEventTest {


    @Test
    public void should_invoke_attack_on_creature() throws Exception {

        // Given
        val testee = CreatureAttackedEvent.builder()
                .attackerId("Id 1")
                .ownerId("Id 2")
                .defenderId("Id 3")
                .build();
        val attacker = CreatureMock.builder()
                .id("Id 1")
                .hitpoints(3)
                .attack(2)
                .build();
        val defender = CreatureMock.builder()
                .id("Id 3")
                .hitpoints(3)
                .attack(1)
                .build();
        val game = Game.builder()
                .player1(Player.builder()
                        .id("Id 2")
                        .spot(Spot.builder()
                                .creature(attacker)
                                .build())
                        .build())
                .player2(Player.builder()
                        .spot(Spot.builder()
                                .creature(defender)
                                .build())
                        .build())
                .build();

        // When
        val updatedGame = testee.invoke(game);

        // Then
        val updatedAttacker = updatedGame.findCreature("Id 1")
                .orElseThrow(() -> new Exception("Attacker not present"));
        assertThat(updatedAttacker.getHitpoints()).isEqualTo(2);
        val updatedDefender = updatedGame.findCreature("Id 3")
                .orElseThrow(() -> new Exception("Defender not present"));
        assertThat(updatedDefender.getHitpoints()).isEqualTo(1);
    }
}