package de.schramm.royalbash.api

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.State.OPEN
import de.schramm.royalbash.infrastructure.gameevent.*
import de.schramm.royalbash.verifyThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(GameController::class, secure = false)
class EventTranslationTest {

    private val gameId = "1"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gameService: GameService

    @BeforeEach
    fun define_mock_behavior() {

        val game = Game(
                gameId,
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                playerOnTurn = Player("Id 2"),
                state = OPEN,
                log = Log())

        every { gameService.commitGameEvent(any(), any()) } returns game
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
                   "\"cardId\": \"Card Id\", " +
                   "\"ownerId\": \"Owner Id\", " +
                   "\"targetPlayerId\": \"Target Player Id\"}" +
                   "}"
        val expectedEvent = CardPlayedOnPlayerEventDTO(
                "Card Id",
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
                   "\"attackerId\": \"Attacker Id\", " +
                   "\"defenderId\": \"Defender Id\", " +
                   "\"ownerId\": \"Owner Id\"}" +
                   "}"
        val expectedEvent = CreatureAttackedEventDTO(
                attackerId = "Attacker Id",
                defenderId = "Defender Id",
                ownerId = "Owner Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_NoOpEvent() {

        // Given
        val json = """{"event": {"type": "NO_OP"}}"""
        val expectedEvent = NoOpEventDTO()

        // When Then
        test(json, expectedEvent)
    }

    @Test
    fun should_translate_PlayerAttackedEvent() {

        // Given
        val json = """{"event": {
                           "type": "PLAYER_ATTACKED", 
                           "creatureId": "Creature Id", 
                           "ownerId": "Owner Id"
                      }}"""
        val expectedEvent = PlayerAttackedEventDTO("Creature Id", "Owner Id")

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
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        val response = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        try {
            verifyThat("commitGameEvent() is called at all") {
                gameService.commitGameEvent(any(), any())
            }
            verifyThat("commitGameEvent() is called with expected Game Id") {
                gameService.commitGameEvent(gameId, any())
            }
            verifyThat("commitGameEvent() is called with expected Game Id and Event") {
                gameService.commitGameEvent(gameId, expectedEvent)
            }
        } catch (error: AssertionError) {
            throw AssertionError("Event Translation failed \nHttp Response: [ \n$response \n]\n${error.message}",
                                 error)
        }
    }

    @TestConfiguration
    class ControllerTestConfig {

        @Bean
        fun gameService() = mockk<GameService>()
    }
}
