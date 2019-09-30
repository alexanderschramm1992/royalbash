package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.combat
import de.schramm.royalbash.domain.findCreature
import de.schramm.royalbash.domain.findPlayer

data class PlayerAttackedEventDTO(val creatureId: String,
                                  val ownerId: String):
        GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val creature = game.findCreature(creatureId)?.takeIf { owner?.findCreature(it) == it }

        return if (owner != null && creature != null)
            game.combat(creature, owner)
        else game
    }
}
