package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.application.Games
import de.schramm.royalbash.domain.Game
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("!persistent")
@Component
class InMemoryRepository: Games {

    private val games = mutableMapOf<String, GameDTO>()

    override fun findAll() = games.values.map { it.toGame() }

    override fun findById(id: String) = games[id]?.toGame()

    override fun save(game: Game) {
        games[game.id] = game.toDTO()
    }

}