package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.effect.DiscardHandcardsEffect

data class MemoryLeak(
        override val id: String,
        override val cost: Int
) : Card {

    override val name = "Memory Leak"
    override val text = "Target player discards 2 handcards."

    val effect = DiscardHandcardsEffect(2)

    override fun invoke(context: Context): Game {
        return effect.invoke(context)
    }
}
