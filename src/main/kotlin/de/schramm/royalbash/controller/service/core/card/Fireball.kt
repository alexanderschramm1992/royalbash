package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.effect.DealDamageToPlayerEffect

class Fireball (
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Fireball"
    override val text = "Deal 2 damage to target player."

    val effect = DealDamageToPlayerEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
