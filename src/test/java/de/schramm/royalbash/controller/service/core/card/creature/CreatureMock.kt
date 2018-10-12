package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Creature
import de.schramm.royalbash.controller.service.core.Game

data class CreatureMock(
        override val id: String,
        override val name: String = "Creature Mock",
        override val hitpoints: Int = 0,
        override val attack: Int = 0,
        override val cost: Int = 0
) : Creature {

    override fun damage(amountOfDamage: Int): Creature {
        return this.copy(hitpoints = hitpoints - amountOfDamage)
    }

    override fun invoke(context: Context): Game {
        return context.game
    }
}
