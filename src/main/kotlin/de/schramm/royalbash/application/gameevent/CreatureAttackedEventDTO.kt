package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class CreatureAttackedEventDTO(
        val attackerInstanceId: String,
        val ownerId: String,
        val defenderInstanceId: String): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game.run {
        val attacker = findCreature(attackerInstanceId)
        val attackerOwner = findPlayer(ownerId)
        val defender = findCreature(defenderInstanceId)
        val defenderOwner = opponentOf(attackerOwner)

        return when {
            attacker == null                           -> logAttackerMissing(uuidGenerator)
            defender == null                           -> logDefenderMissing(uuidGenerator, attacker)
            attackerOwner == null                      -> logOwnerOfAttackerMissing(uuidGenerator, attacker)
            defenderOwner == null                      -> logOwnerOfDefenderMissing(uuidGenerator, attacker, defender)
            attackerOwner != playerOnTurn              -> logOwnerOfAttackerNotOnTurn(uuidGenerator, attacker,
                                                                                      attackerOwner)
            attackerOwner == defenderOwner             -> logOwnerIsDefender(uuidGenerator, attacker, attackerOwner)
            defenderOwner != opponentOf(attackerOwner) -> logDefenderIsNotOpponent(uuidGenerator, attacker,
                                                                                   attackerOwner,
                                                                                   defenderOwner)
            attacker !in attackerOwner.creatures       -> logAttackerNotOnOwnerSpots(uuidGenerator, attacker,
                                                                                     attackerOwner)
            defender !in defenderOwner.creatures       -> logDefenderNotOnOwnerSpots(uuidGenerator,
                                                                                     attacker,
                                                                                     defender,
                                                                                     defenderOwner)
            else                                       -> attacker.attack(AttackCreatureContext(uuidGenerator,
                                                                                                this,
                                                                                                attackerOwner,
                                                                                                defender))
        }
    }
}
