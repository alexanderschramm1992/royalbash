package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class NoOpCreature(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int
) : Creature {

    override val name = "NoOpCreature"
    override val text = "NoOp Creature Text"
    override val image = "NoOp Image URL"

    override fun invoke(context: Context): Game = context.run {
        val owner = game.findPlayer(ownerId)
        val targetSpot = game.findSpot(targetSpotId)

        return when {
            owner == null               -> game.logOwnerMissing(uuidGenerator, this@NoOpCreature)
            game.playerOnTurn != owner  -> game.logPlayerNotOnTurn(uuidGenerator, this@NoOpCreature, owner)
            targetSpot == null          -> game.logTargetSpotMissing(uuidGenerator, this@NoOpCreature)
            targetSpot.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@NoOpCreature)
            owner.resources >= cost     -> game.logResourcesMissing(uuidGenerator, this@NoOpCreature)
            else                        -> game.updatePlayer(owner to owner
                    .reduceResourcesBy(cost)
                    .removeHandcard(this@NoOpCreature)
                    .updateSpot(targetSpot to targetSpot.place(this@NoOpCreature)))
                    .logInvokationOnSpot(uuidGenerator, this@NoOpCreature, owner)
        }
    }

    override fun damage(amountOfDamage: Int): Creature {
        return this.copy(hitpoints = hitpoints - amountOfDamage)
    }
}
