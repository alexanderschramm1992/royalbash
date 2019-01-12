package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game
import java.util.*

class GameRepositoryMock : GameRepository {

    private val list: MutableList<Game> = mutableListOf()

    override fun <S : Game> save(game: S): S? {
        list.add(game)
        return game
    }

    override fun findAll(): Iterable<Game>? {
        return list
    }

    override fun count(): Long {
        return list.size.toLong()
    }

    override fun delete(entity: Game) {
        list.remove(entity)
    }

    override fun deleteAll() {
        list.removeAll(list)
    }

    override fun deleteAll(games: MutableIterable<Game>) {
        games.forEach { game -> this.delete(game) }
    }

    override fun deleteById(id: String) {
        list.filter { game -> game.id == id }
    }

    override fun <S: Game?> saveAll(games: MutableIterable<S>): MutableIterable<S> {
        games.forEach { game -> when(game) { is Game -> this.save(game) }}
        return games
    }

    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Game> {
        return list.filter { game -> ids.contains(game.id) }
                .toCollection(mutableListOf())
    }

    override fun existsById(id: String): Boolean {
        return list.any { game -> game.id == id }
    }

    override fun findById(id: String): Optional<Game> {
        return list.filter { game -> game.id == id }
                .map { game -> Optional.of(game) }
                .first()
    }
}
