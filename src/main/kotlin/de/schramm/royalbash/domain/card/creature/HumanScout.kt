package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logDrawEffect
import de.schramm.royalbash.domain.card.logInvokationOnSpot
import de.schramm.royalbash.domain.card.logResourcesMissing
import de.schramm.royalbash.domain.card.logTargetSpotOccupied

data class HumanScout(override val id: String,
                      override val instanceId: String,
                      override val hitpoints: Int,
                      override val attack: Int,
                      override val cost: Int): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val name = "Human Scout"
    override val text = "When Human Scout is invoked, draw 2 cards."
    override val image = "human-scout-card.png"

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        when {
            target.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@HumanScout)
            owner.resources < cost  -> game.logResourcesMissing(uuidGenerator, this@HumanScout)
            else                    -> game
                    .updatePlayer(owner.id) {
                        it.reduceResourcesBy(cost)
                                .removeHandcard(this@HumanScout)
                                .updateSpot(target to target.place(this@HumanScout))
                    }
                    .logInvokationOnSpot(uuidGenerator, this@HumanScout, owner)
                    .updatePlayer(owner.id) { it.drawCards(2) }
                    .logDrawEffect(uuidGenerator, this@HumanScout, owner, 2)
        }
    }

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)
}
