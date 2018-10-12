package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

data class CreatureAttackedEvent(
        val attackerId: String,
        val ownerId: String,
        val defenderId: String
) : GameEvent {

    constructor(): this(attackerId = "", ownerId = "", defenderId = "")

    override fun invoke(game: Game): Game {
        return game
                .findPlayer(ownerId)
                .map { owner -> game.findCreature(attackerId)
                        .map { attacker -> game
                                .findCreature(defenderId)
                                .map { defender -> game.combat(attacker, owner, defender) }
                                .orElse(game) }
                        .orElse(game) }
                .orElse(game)
    }
}
