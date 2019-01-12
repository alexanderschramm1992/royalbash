package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.UUIDGenerator
import de.schramm.royalbash.application.gameevent.GameEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.StreamSupport

@Service
open class GameService @Autowired
constructor(
        private val uuidGenerator: UUIDGenerator,
        private val gameRepository: GameRepository
) {

    fun retrieveGame(gameId: String): Optional<Game> {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .filter { game -> gameId == game.id }
                .findFirst()
    }

    fun createGame(account1Id: String, account2Id: String): Game {

        val player1Id = uuidGenerator.generateId()
        val player2Id = uuidGenerator.generateId()

        val game = Game(
                id = uuidGenerator.generateId(),
                player1 = Player(id = player1Id, name = account1Id),
                player2 = Player(id = player2Id, name = account2Id))

        gameRepository.save(game)

        return game
    }

    open fun commitGameEvent(gameId: String, gameEvent: GameEvent): Optional<Game> {
        println("""Received event $gameEvent for game $gameId""")
        return retrieveGame(gameId).map { gameEvent.invoke(it) }
    }

    fun retrieveGameIds(): List<String> {
        return gameRepository.findAll()
                .map { it.id }
    }
}
