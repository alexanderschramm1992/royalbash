package de.schramm.royalbash.api

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.application.gameevent.*
import de.schramm.royalbash.application.toExternalModel
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.State.OPEN
import de.schramm.royalbash.infrastructure.JacksonConfig
import de.schramm.royalbash.infrastructure.controller.GameController
import de.schramm.royalbash.verifyThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@TestConfiguration
class ControllerTestConfig {
    @Bean
    fun gameService() = mockk<GameService>()
}

@Import(JacksonConfig::class, ControllerTestConfig::class)
@WebMvcTest(GameController::class, secure = false)
class EventTranslationTest {

    companion object {
        private const val gameId = "1"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gameService: GameService

    @BeforeEach
    fun define_mock_behavior() {

        val game = Game(gameId,
                        player1 = Player("Id 2"),
                        player2 = Player("Id 3"),
                        playerOnTurn = Player("Id 2"),
                        state = OPEN)

        every { gameService.commitGameEvent(any(), any()) } returns game.toExternalModel()
    }

    @Test
    fun should_translate_CardDrawnEvent() {

        // Given
        val json = """{
            "event": {
                "type": "CARD_DRAWN",
                "amountOfCards": 1,
                "playerId": "Player Id"
            }
        }"""
        val expectedEvent = CardDrawnEventDTO("Player Id", 1)

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_CardPlayedOnPlayerEvent() {

        // Given
        val json = "{\"event\": {" +
                   "\"type\": \"CARD_PLAYED_ON_PLAYER\", " +
                   "\"cardInstanceId\": \"Card InstanceId\", " +
                   "\"ownerId\": \"Owner Id\", " +
                   "\"targetPlayerId\": \"Target Player Id\"}" +
                   "}"
        val expectedEvent = CardPlayedOnPlayerEventDTO(
                "Card InstanceId",
                "Owner Id",
                "Target Player Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_CreatureAttackedEvent() {

        // Given
        val json = "{\"event\": {" +
                   "\"type\": \"CREATURE_ATTACKED\", " +
                   "\"attackerInstanceId\": \"Attacker InstanceId\", " +
                   "\"defenderInstanceId\": \"Defender InstanceId\", " +
                   "\"ownerId\": \"Owner Id\"}" +
                   "}"
        val expectedEvent = CreatureAttackedEventDTO(
                attackerInstanceId = "Attacker InstanceId",
                defenderInstanceId = "Defender InstanceId",
                ownerId = "Owner Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_NoOpEvent() {

        // Given
        val json = """{"event": {
                            "type": "NO_OP", 
                            "noopValue": "value"
                       }}"""
        val expectedEvent = NoOpEventDTO("value")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_PlayerAttackedEvent() {

        // Given
        val json = """{"event": {
                           "type": "PLAYER_ATTACKED", 
                           "creatureInstanceId": "Creature InstanceId", 
                           "ownerId": "Owner Id"
                      }}"""
        val expectedEvent = PlayerAttackedEventDTO("Creature InstanceId", "Owner Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_TurnEndedEvent() {

        // Given
        val json = """{"event": {
                        "type": "TURN_ENDED", 
                        "playerId": "Player Id"
                      }}"""
        val expectedEvent = TurnEndedEventDTO("Player Id")

        // When Then
        test(json, expectedEvent)
    }

    private fun test(json: String, expectedEvent: GameEventDTO) {

        // Given
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content(json)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)

        // When
        val mvcResult = mockMvc.perform(requestBuilder).andReturn()
        val resolvedException = mvcResult.resolvedException

        // Then
        if (resolvedException != null) {
            throw java.lang.AssertionError("Failed to resolve HTTP call", resolvedException)
        }

        verifyThat("commitGameEvent() is called at all") {
            gameService.commitGameEvent(any(), any())
        }
        verifyThat("commitGameEvent() is called with expected Game Id") {
            gameService.commitGameEvent(gameId, any())
        }
        verifyThat("commitGameEvent() is called with expected Game Id and Event") {
            gameService.commitGameEvent(gameId, expectedEvent)
        }
    }
}
