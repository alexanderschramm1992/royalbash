package de.schramm.royalbash.tdd.controller;

import de.schramm.royalbash.tdd.controller.service.GameService;
import de.schramm.royalbash.tdd.controller.service.core.Game;
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
        Mockito.when(gameService.retrieveGame()).thenReturn(Game.builder().build());

        val requestBuilder = MockMvcRequestBuilders
                .get("/game")
                .accept(MediaType.APPLICATION_JSON);

        // When
        val result = mockMvc.perform(requestBuilder).andReturn();

        // Then
        val expected = "{}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}