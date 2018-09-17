package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import lombok.val;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDrawnEventTest {

    @Test
    public void should_invoke_draw_card() {

        // Given
        val card = NoOpCard.builder().build();
        val player1 = Player.builder()
                .id("Id 1")
                .clearHandcards()
                .deckcard(card)
                .build();
        val game = Game.builder()
                .player1(player1)
                .player2(Player.builder().build())
                .build();
        val testee = CardDrawnEvent.builder()
                .playerId("Id 1")
                .amountOfCards(1)
                .build();

        // When
        val updatedGame = testee.invoke(game);

        // Then
        assertThat(updatedGame).isNotNull();
        assertThat(updatedGame.getPlayer1().getHandcards().collect(Collectors.toList())).hasSize(1);
    }
}
