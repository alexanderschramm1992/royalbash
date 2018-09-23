package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.GameService;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.State;
import de.schramm.royalbash.controller.service.gameevent.*;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GameController.class, secure = false)
public class EventTranslationTest {

    private final String gameId = "Id 1";

    @Autowired
    private
    MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Before
    public void define_mock_behavior() {
        Mockito.when(gameService.commitGameEvent(any(), any())).thenReturn(Game.builder()
            .player1(Player.builder().build())
            .player2(Player.builder().build())
            .playerOnTurn(Player.builder().build())
            .state(State.OPEN)
            .build());
    }

    @Test
    public void should_translate_CardDrawnEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {" +
                        "\"type\": \"CARD_DRAWN\", " +
                        "\"amountOfCards\": 1, " +
                        "\"playerId\": \"Player Id\"}" +
                "}",
                gameId
        );
        val expectedEvent = CardDrawnEvent.builder()
                .amountOfCards(1)
                .playerId("Player Id")
                .build();

        // When Then
        test(json, expectedEvent);
    }

    @Test
    public void should_translate_CardPlayedOnPlayerEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {" +
                        "\"type\": \"CARD_PLAYED_ON_PLAYER\", " +
                        "\"cardId\": \"Card Id\", " +
                        "\"ownerId\": \"Owner Id\", " +
                        "\"targetPlayerId\": \"Target Player Id\"}" +
                "}",
                gameId
        );
        val expectedEvent = CardPlayedOnPlayerEvent.builder()
                .cardId("Card Id")
                .ownerId("Owner Id")
                .targetPlayerId("Target Player Id")
                .build();

        // When Then
        test(json, expectedEvent);
    }

    @Test
    public void should_translate_CreatureAttackedEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {" +
                        "\"type\": \"CREATURE_ATTACKED\", " +
                        "\"attackerId\": \"Attacker Id\", " +
                        "\"defenderId\": \"Defender Id\", " +
                        "\"ownerId\": \"Owner Id\"}" +
                "}",
                gameId
        );
        val expectedEvent = CreatureAttackedEvent.builder()
                .attackerId("Attacker Id")
                .defenderId("Defender Id")
                .ownerId("Owner Id")
                .build();

        // When Then
        test(json, expectedEvent);
    }

    @Test
    public void should_translate_NoOpEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {\"type\": \"NO_OP\"}}",
                gameId
        );
        val expectedEvent = NoOpEvent.builder().build();

        // When Then
        test(json, expectedEvent);
    }

    @Test
    public void should_translate_PlayerAttackedEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {" +
                        "\"type\": \"PLAYER_ATTACKED\", " +
                        "\"creatureId\": \"Creature Id\", " +
                        "\"ownerId\": \"Owner Id\"}" +
                "}",
                gameId
        );
        val expectedEvent = PlayerAttackedEvent.builder()
                .creatureId("Creature Id")
                .ownerId("Owner Id")
                .build();

        // When Then
        test(json, expectedEvent);
    }

    @Test
    public void should_translate_TurnEndedEvent() throws Exception {

        // Given
        val json = String.format(
                "{\"gameId\": \"%s\", \"event\": {" +
                        "\"type\": \"TURN_ENDED\", " +
                        "\"playerId\": \"Player Id\"}" +
                "}",
                gameId
        );
        val expectedEvent = TurnEndedEvent.builder()
                .playerId("Player Id")
                .build();

        // When Then
        test(json, expectedEvent);
    }

    private void test(String json, GameEvent expectedEvent) throws Exception {

        // Given
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/event")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // When
        mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        verify(gameService, times(1)).commitGameEvent(gameId, expectedEvent);
    }
}
