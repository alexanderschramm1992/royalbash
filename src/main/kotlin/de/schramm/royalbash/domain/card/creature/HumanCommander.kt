package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.creature.CreatureType.*
import de.schramm.royalbash.domain.card.logInvokationOnSpot
import de.schramm.royalbash.domain.card.logResourcesMissing
import de.schramm.royalbash.domain.card.logTargetSpotOccupied

data class HumanCommander(override val id: String,
                          override val instanceId: String,
                          override val hitpoints: Int,
                          override val attack: Int,
                          override val cost: Int): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val type = HUMAN
    override val name = "Human Commander"
    override val text = "When Human Commander is invoked, gain +1 Attack for each Human you own."
    override val image = "human-commander-card.png"

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        when {
            target.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@HumanCommander)
            owner.resources < cost  -> game.logResourcesMissing(uuidGenerator, this@HumanCommander)
            else                    -> game
                    .updatePlayer(owner.id) {
                        it.reduceResourcesBy(cost)
                                .removeHandcard(this@HumanCommander)
                                .updateSpot(target to target.place(this@HumanCommander))
                    }
                    .logInvokationOnSpot(uuidGenerator, this@HumanCommander, owner)
                    .updatePlayer(owner.id) {
                        it.updateCreatureOnSpot(target) { creature ->
                            creature?.increaseAttackBy(owner.creaturesOfType(HUMAN).size)
                        }
                    }
        }
    }

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)

    override fun increaseAttackBy(amountOfAttack: Int) = copy(attack = attack + amountOfAttack)
}
