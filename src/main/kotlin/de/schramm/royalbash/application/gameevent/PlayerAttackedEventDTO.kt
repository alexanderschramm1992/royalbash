package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class PlayerAttackedEventDTO(val creatureInstanceId: String,
                                  val ownerId: String): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game.run {
        val attacker = findCreature(creatureInstanceId)
        val attackerOwner = findPlayer(ownerId)
        val defender = game.opponentOf(attackerOwner)

        return when {
            attacker == null                     -> logAttackerMissing(uuidGenerator)
            defender == null                     -> logDefenderMissing(uuidGenerator, attacker)
            attackerOwner == null                -> logOwnerOfAttackerMissing(uuidGenerator, attacker)
            attackerOwner != playerOnTurn        -> logOwnerOfAttackerNotOnTurn(uuidGenerator, attacker, attackerOwner)
            attacker !in attackerOwner.creatures -> logAttackerNotOnOwnerSpots(uuidGenerator, attacker, attackerOwner)
            else                                 -> attacker.attack(AttackPlayerContext(uuidGenerator,
                                                                                        this,
                                                                                        attackerOwner,
                                                                                        defender))
        }
    }
}
