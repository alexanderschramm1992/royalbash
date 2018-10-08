package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.effect.DiscardHandcardsEffect

data class MemoryLeak(
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Memory Leak"

    val effect = DiscardHandcardsEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
