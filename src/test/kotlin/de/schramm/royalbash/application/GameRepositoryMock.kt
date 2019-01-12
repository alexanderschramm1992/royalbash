package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game

class GameRepositoryMock : GameRepository {

    private val list: MutableList<Game> = mutableListOf()

    override fun <S : Game> save(entity: S): S? {
        list.add(entity)
        return entity
    }

    override fun <S : Game> save(entities: Iterable<S>): Iterable<S>? {
        entities.forEach { list.add(it) }
        return entities
    }

    override fun findOne(s: String): Game? {
        return list.find { it.id == s }
    }

    override fun exists(s: String): Boolean {
        return list.any { it.id == s }
    }

    override fun findAll(): Iterable<Game>? {
        return list
    }

    override fun findAll(strings: Iterable<String>): Iterable<Game>? {
        return list.filter { strings.contains(it.id) }
    }

    override fun count(): Long {
        return list.size.toLong()
    }

    override fun delete(s: String) {
        list.removeIf { it.id == s }
    }

    override fun delete(entity: Game) {
        list.remove(entity)
    }

    override fun delete(entities: Iterable<Game>) {
        list.removeIf { entities.contains(it) }
    }

    override fun deleteAll() {
        list.removeAll(list)
    }
}
