package de.schramm.royalbash.tdd.controller.service.core.card;

import de.schramm.royalbash.tdd.controller.service.core.Context;
import de.schramm.royalbash.tdd.controller.service.core.Game;
import de.schramm.royalbash.tdd.controller.service.core.Player;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealDamageToPlayerCardTest {

    @Test
    public void should_deal_damage_to_target_player() {

        // Given
        val testee = DealDamageToPlayerCard.builder()
                .amountOfDamage(3)
                .build();
        val player1 = Player.builder()
                .handcard(testee)
                .build();
        val player2 = Player.builder()
                .hitpoints(20)
                .build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player2)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer2().getHitpoints()).isEqualTo(17);
    }

}