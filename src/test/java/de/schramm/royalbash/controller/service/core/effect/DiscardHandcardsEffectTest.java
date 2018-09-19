package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscardHandcardsEffectTest {

    @Test
    public void should_discard_handcards_for_target_player() {

        // Given
        val testee = DiscardHandcardsEffect.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder()
                .handcard(NoOpCard.builder().build())
                .handcard(NoOpCard.builder().build())
                .build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player1)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer1().getHandcards()).isEmpty();
    }

    @Test
    public void should_do_nothing_if_target_player_has_no_handcards() {

        // Given
        val testee = DiscardHandcardsEffect.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder().build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player1)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer1().getHandcards()).isEmpty();
    }
}
