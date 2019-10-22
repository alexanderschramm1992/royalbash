package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game

interface Games {
    fun findAll(): Collection<Game>
    fun findById(id: String): Game?
    fun save(game: Game)
    fun deleteById(id: String)
}
