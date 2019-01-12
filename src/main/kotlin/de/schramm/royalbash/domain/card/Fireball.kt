package de.schramm.royalbash.domain.card

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.effect.DealDamageToPlayerEffect

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
