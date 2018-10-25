package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Creature
import de.schramm.royalbash.controller.service.core.Game

data class NoOpCreature(
        override val id: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int
) : Creature {

    override val name: String = "NoOpCreature"
    override val text = "NoOp Creature Text"

    override fun invoke(context: Context): Game {
        return CreatureUtil.spawnCreature(this, context)
    }

    override fun damage(amountOfDamage: Int): Creature {
        return this.copy(hitpoints = hitpoints - amountOfDamage)
    }
}
