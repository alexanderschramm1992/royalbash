package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.Spot;
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveCreatureEffectTest {

    @Test
    public void should_remove_target_creature_from_spot_an_add_it_to_deposit() {

        // Given
        val testee = RemoveCreatureEffect.builder().build();
        val creature = CreatureMock.builder().build();
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
        assertThat(updatedGame.getPlayer1().findCreature(creature)).isNotPresent();
        assertThat(updatedGame.getPlayer1().getDepositcards())
                .hasSize(1)
                .contains(creature);
    }

}