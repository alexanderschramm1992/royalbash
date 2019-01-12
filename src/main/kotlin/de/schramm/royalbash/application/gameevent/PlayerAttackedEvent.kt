package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game

data class PlayerAttackedEvent(
        val creatureId: String,
        val ownerId: String
) : GameEvent {

    constructor(): this(creatureId = "", ownerId = "")

    override fun invoke(game: Game): Game {
        return game
                .findPlayer(ownerId)
                .map { player -> game.findCreature(creatureId)
                        .map { creature -> game .combat(creature, player) }
                        .orElse(game) }
                .orElse(game)
    }
}
