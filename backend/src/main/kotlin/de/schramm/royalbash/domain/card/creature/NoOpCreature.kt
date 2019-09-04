package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.spawnCreature

data class NoOpCreature(
        override val id: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int
) : Creature {

    override val name = "NoOpCreature"
    override val text = "NoOp Creature Text"
    override val image = "NoOp Image URL"

    override fun invoke(context: Context): Game {
        return this.spawnCreature(context)
    }

    override fun damage(amountOfDamage: Int): Creature {
        return this.copy(hitpoints = hitpoints - amountOfDamage)
    }
}
