package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.GameService;
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
        Mockito.when(gameService.retrieveGame(any())).thenReturn(Game.builder().build());

        val requestBuilder = MockMvcRequestBuilders
                .get("/game/1")
                .accept(MediaType.APPLICATION_JSON);

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        JSONAssert.assertEquals("{}", result, false);
    }
}