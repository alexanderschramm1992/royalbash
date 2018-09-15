package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.GameService;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.gameevent.NoOpEvent;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void should_deliver_game() throws Exception {

        // Given
        val game = Game.builder()
                .player1(Player.builder()
                        .name("Player 1")
                        .build())
                .build();
        Mockito.when(gameService.retrieveGame(any())).thenReturn(game);

        val requestBuilder = MockMvcRequestBuilders
                .get("/game/1")
                .accept(MediaType.APPLICATION_JSON);

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        JSONAssert.assertEquals("{\"player1\": {\"name\": \"Player 1\"}}", result, false);
    }

    @Test
    public void should_deliver_new_game() throws Exception {

        // Given
        val accountId1 = "Account 1";
        val accountId2 = "Account 2";
        val game = Game.builder()
                .player1(Player.builder()
                        .name(accountId1)
                        .build())
                .player2(Player.builder()
                        .name(accountId2)
                        .build())
                .build();
        Mockito.when(gameService.createGame(accountId1, accountId2)).thenReturn(game);

        val requestBuilder = MockMvcRequestBuilders
                .post("/game")
                .content(String.format(
                        "{\"accountId1\": \"%s\", \"accountId2\": \"%s\"}",
                        accountId1,
                        accountId2
                ))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        JSONAssert.assertEquals(
                "{\"player1\": {\"name\": \"Account 1\"}, \"player2\": {\"name\": \"Account 2\"}}",
                result,
                false
        );
    }

    @Test
    public void should_resolve_event_and_return_updated_game() throws Exception {

        // Given
        val gameId = "Id 1";
        val game = Game.builder().build();
        Mockito.when(gameService.commitGameEvent(gameId, NoOpEvent.builder().build())).thenReturn(game);
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/event")
                .content(String.format(
                        "{\"gameId\": \"%s\", \"event\": {\"type\": \"NO_OP\"}}",
                        gameId
                ))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        verify(gameService, times(1)).commitGameEvent(gameId, NoOpEvent.builder().build());
        JSONAssert.assertEquals(
                "{}",
                result,
                false
        );
    }
}