package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class PlayerAttackedEventDTO(val creatureId: String,
                                  val ownerId: String):
        GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val creature = game.findCreature(creatureId)?.takeIf { owner?.findCreature(it) == it }

        return if (owner != null && creature != null)
            game.combat(creature, owner)
                    .log(uuidGenerator.generateId(),
                            "${creature.name} of ${owner.name} has attacked ${game.opponentOf(owner).name}")
        else game
    }
}
