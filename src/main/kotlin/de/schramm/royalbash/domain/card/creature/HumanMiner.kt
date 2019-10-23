package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class HumanMiner(override val id: String,
                      override val instanceId: String,
                      override val hitpoints: Int,
                      override val attack: Int,
                      override val cost: Int): Creature {

    override val name = "Human Miner"
    override val text = "When Human Miner enters the battlefield, gain 3 resources."
    override val image = "FantasyCharacters_h_miner_male_bg.png"

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val targetSpot = game.findSpot(targetSpotId)

        return when {
            owner == null                       -> game.logOwnerMissing(uuidGenerator, this@HumanMiner)
            owner != game.playerOnTurn          -> game.logPlayerNotOnTurn(uuidGenerator, this@HumanMiner, owner)
            targetSpot == null                  -> game.logTargetSpotMissing(uuidGenerator, this@HumanMiner)
            targetSpot.creature != null         -> game.logTargetSpotOccupied(uuidGenerator, this@HumanMiner)
            owner.resources <= cost             -> game.logResourcesMissing(uuidGenerator, this@HumanMiner)
            this@HumanMiner !in owner.handcards -> game.logHandcardMissing(uuidGenerator, this@HumanMiner, owner)
            else                                -> game.updatePlayer(owner to owner
                    .reduceResourcesBy(cost)
                    .removeHandcard(this@HumanMiner)
                    .updateSpot(targetSpot to targetSpot.place(this@HumanMiner)))
                    .logInvokationOnSpot(uuidGenerator, this@HumanMiner, owner)
                    .updatePlayer(owner to owner.increaseResourcesBy(3))
                    .logGainResourcesEffect(uuidGenerator, this@HumanMiner, owner, 3)
        }
    }

    override fun damage(amountOfDamage: Int): Creature =
            this.copy(hitpoints = hitpoints - amountOfDamage)
}
