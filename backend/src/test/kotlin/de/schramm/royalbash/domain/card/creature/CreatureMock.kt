package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game

data class CreatureMock(
        override val id: String,
        override val name: String = "Creature Mock",
        override val text: String = "Creature Mock Text",
        override val image: String? = "Creature Mock Image URL",
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
