package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class GoblinGuard(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int): Creature {

    override val name = "Goblin Guard"
    override val text = "When Goblin Guard enters the battlefield, target player discards a card."
    override val image = "FantasyCharacters_guard_goblin_bg.png"

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val opponent = game.opponentOf(owner)
        val targetSpot = game.findSpot(targetSpotId)

        return when {
            owner == null                        -> game.logOwnerMissing(uuidGenerator, this@GoblinGuard)
            owner != game.playerOnTurn           -> game.logPlayerNotOnTurn(uuidGenerator, this@GoblinGuard, owner)
            opponent == null                     -> game.logOpponentMissing(uuidGenerator, this@GoblinGuard)
            targetSpot == null                   -> game.logTargetSpotMissing(uuidGenerator, this@GoblinGuard)
            targetSpot.creature != null          -> game.logTargetSpotOccupied(uuidGenerator, this@GoblinGuard)
            owner.resources <= cost              -> game.logResourcesMissing(uuidGenerator, this@GoblinGuard)
            this@GoblinGuard !in owner.handcards -> game.logHandcardMissing(uuidGenerator, this@GoblinGuard, owner)
            else                                 -> game.updatePlayer(owner to owner
                    .reduceResourcesBy(cost)
                    .removeHandcard(this@GoblinGuard)
                    .updateSpot(targetSpot to targetSpot.place(this@GoblinGuard)))
                    .logInvokationOnSpot(uuidGenerator, this@GoblinGuard, owner)
                    .updatePlayer(opponent to opponent.discardCards(1))
                    .logDiscardEffect(uuidGenerator, this@GoblinGuard, opponent, 1)
        }
    }

    override fun damage(amountOfDamage: Int): Creature =
            this.copy(hitpoints = hitpoints - amountOfDamage)
}
