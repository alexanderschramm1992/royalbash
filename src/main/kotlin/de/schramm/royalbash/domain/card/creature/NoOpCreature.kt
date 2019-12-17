package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.card.creature.CreatureType.*

data class NoOpCreature(override val id: String,
                        override val instanceId: String,
                        override val hitpoints: Int = 1,
                        override val attack: Int = 0,
                        override val cost: Int = 0): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val type = MISC
    override val name = "NoOpCreature"
    override val text = "NoOp Creature Text"
    override val image = "NoOp Image URL"

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)

    override fun increaseAttackBy(amountOfAttack: Int) = copy(attack = attack + amountOfAttack)
}
