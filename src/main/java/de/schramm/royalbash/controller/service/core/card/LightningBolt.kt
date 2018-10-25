package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.effect.DealDamageToCreatureEffect

data class LightningBolt (
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Lightning Bolt"
    override val text = ""

    val effect = DealDamageToCreatureEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
