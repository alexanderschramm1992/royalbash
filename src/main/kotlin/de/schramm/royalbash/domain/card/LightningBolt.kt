package de.schramm.royalbash.domain.card

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.effect.DealDamageToCreatureEffect

data class LightningBolt (
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Lightning Bolt"
    override val text = "Deal 2 damage to target creature."

    val effect = DealDamageToCreatureEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
