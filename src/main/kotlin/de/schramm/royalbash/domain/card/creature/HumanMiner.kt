package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logGainResourcesEffect
import de.schramm.royalbash.domain.card.logInvokationOnSpot
import de.schramm.royalbash.domain.card.logResourcesMissing
import de.schramm.royalbash.domain.card.logTargetSpotOccupied

data class HumanMiner(override val id: String,
                      override val instanceId: String,
                      override val hitpoints: Int,
                      override val attack: Int,
                      override val cost: Int): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val name = "Human Miner"
    override val text = "When Human Miner is invoked, gain 3 resources."
    override val image = "human-miner-card.png"

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        when {
            target.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@HumanMiner)
            owner.resources < cost  -> game.logResourcesMissing(uuidGenerator, this@HumanMiner)
            else                    -> game
                    .updatePlayer(owner.id) {
                        it.reduceResourcesBy(cost)
                                .removeHandcard(this@HumanMiner)
                                .updateSpot(target to target.place(this@HumanMiner))
                    }
                    .logInvokationOnSpot(uuidGenerator, this@HumanMiner, owner)
                    .updatePlayer(owner.id) { it.increaseResourcesBy(3) }
                    .logGainResourcesEffect(uuidGenerator, this@HumanMiner, owner, 3)
        }
    }

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)
}
