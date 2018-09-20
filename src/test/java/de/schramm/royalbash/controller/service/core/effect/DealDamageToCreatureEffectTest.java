package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Spot;
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock;
import lombok.val;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DealDamageToCreatureEffectTest {

    @Test
    public void should_deal_damage_to_target_creature() throws Exception {

        // Given
        val testee = DealDamageToCreatureEffect.builder()
                .amountOfDamage(2)
                .build();
        val creature = CreatureMock.builder()
                .id("Id 1")
                .hitpoints(5)
                .build();
        val game = Game.builder()
                .player1(Player.builder()
                        .spot(Spot.builder()
                                .creature(creature)
                                .build())
                        .build())
                .player2(Player.builder().build())
                .build();
        val context = Context.builder()
                .game(game)
                .targetCreature(creature)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame).isNotNull();
        val updatedCreature = updatedGame.findCreature("Id 1")
                .orElseThrow(() -> new Exception("Creature not present"));
        assertThat(updatedCreature.getHitpoints()).isEqualTo(3);
    }
}
