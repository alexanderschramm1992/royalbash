package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.effect.DrawHandcardsEffect

data class ByondInsight(
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Beyond Insight"
    override val text = ""
    val effect = DrawHandcardsEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
