package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class CreatureAttackedEventDTO(
        val attackerId: String,
        val ownerId: String,
        val defenderId: String) : GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val attacker = game.findCreature(attackerId)?.takeIf { owner?.findCreature(it) == it }
        val defender = game.findCreature(defenderId)?. takeUnless { owner?.findCreature(it) == it }

        return if (owner != null && attacker != null && defender != null)
            game.combat(attacker, owner, defender)
                    .log(uuidGenerator.id(),
                         "${attacker.name} of ${owner.name} has attacked ${defender.name} of ${game.opponentOf(
                                 owner).name}")
        else game
    }
}
