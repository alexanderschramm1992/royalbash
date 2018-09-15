package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscardHandscardsCardTest {

    @Test
    public void should_discard_handcards_of_target_player() {

        // Given
        val testee = DiscardHandscardsCard.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder()
                .handcard(testee)
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
                .targetPlayer(player2)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        Assertions.assertThat(updatedGame.getPlayer2().getHandcards().collect(Collectors.toList())).hasSize(0);
    }

    @Test
    public void should_do_nothing_if_target_player_has_no_handcards() {

        // Given
        val testee = DiscardHandscardsCard.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder()
                .handcard(testee)
                .build();
        val player2 = Player.builder()
                .clearHandcards()
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
        Assertions.assertThat(updatedGame.getPlayer2().getHandcards().collect(Collectors.toList())).hasSize(0);
    }
}