package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.application.GameRepository
import de.schramm.royalbash.domain.Game
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Profile("persistent")
@Component
class MongoRepository: GameRepository {

    lateinit var repository: MongoRepository<GameDTO, String>

    override fun findAll() = repository.findAll().map { it.toGame() }

    override fun findById(id: String) = repository.findById(id)
                .map { it.toGame() }
                .orElse(null)

    override fun save(game: Game) {
        repository.save(game.toDTO())
    }
}
