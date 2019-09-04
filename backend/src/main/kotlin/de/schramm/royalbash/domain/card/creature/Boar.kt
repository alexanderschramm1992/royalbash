package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.spawnCreature

data class Boar(
        override val id: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int): Creature {

    override val name = "Wild Boar"
    override val text = ""
    override val image = "FantasyCharacters_boar_bg.png"

    override fun damage(amountOfDamage: Int) =
            this.copy(hitpoints = hitpoints - amountOfDamage)

    override fun invoke(context: Context): Game = this.spawnCreature(context)
}
