package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game

interface GameRepository {
    fun findAll(): Collection<Game>
    fun findById(id: String): Game?
    fun save(game: Game)
}
