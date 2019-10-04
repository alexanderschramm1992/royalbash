package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.effect.GainResourcesEffect
import de.schramm.royalbash.domain.spawnCreature

data class HumanMiner(override val id: String,
                      override val instanceId: String,
                      override val hitpoints: Int,
                      override val attack: Int,
                      override val cost: Int) : Creature {

    override val name = "Human Miner"
    override val text = "When Human Miner enters the battlefield, gain 3 resources."
    override val image = "FantasyCharacters_h_miner_male_bg.png"

    val effect = GainResourcesEffect(3)

    override fun invoke(context: Context): Game {

        val updatedContext = this.spawnCreature(context)

        return effect.invoke(updatedContext)
    }

    override fun damage(amountOfDamage: Int): Creature =
            this.copy(hitpoints = hitpoints - amountOfDamage)

}
