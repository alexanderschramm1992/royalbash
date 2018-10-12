package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

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
