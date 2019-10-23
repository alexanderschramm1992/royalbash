package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class Boar(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int): Creature {

    override val name = "Wild Boar"
    override val text = ""
    override val image = "FantasyCharacters_boar_bg.png"

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val targetSpot = game.findSpot(targetSpotId)

        return when {
            owner == null               -> game.logOwnerMissing(uuidGenerator, this@Boar)
            game.playerOnTurn != owner  -> game.logPlayerNotOnTurn(uuidGenerator, this@Boar, owner)
            targetSpot == null          -> game.logTargetSpotMissing(uuidGenerator, this@Boar)
            targetSpot.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@Boar)
            owner.resources >= cost     -> game.logResourcesMissing(uuidGenerator, this@Boar)
            else                        -> game.updatePlayer(owner to owner
                    .reduceResourcesBy(cost)
                    .removeHandcard(this@Boar)
                    .updateSpot(targetSpot to targetSpot.place(this@Boar)))
                    .logInvokationOnSpot(uuidGenerator, this@Boar, owner)
        }
    }

    override fun damage(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)
}
