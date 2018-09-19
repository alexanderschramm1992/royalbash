package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoOpCardTest {

    @Test
    public void should_not_alter_game() {

        // Given
        val testee = NoOpCard.builder().build();
        val player1 = Player.builder()
                .handcard(testee)
                .build();
        val player2 = Player.builder().build();
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
        Assertions.assertThat(updatedGame).isEqualTo(game);
    }

}