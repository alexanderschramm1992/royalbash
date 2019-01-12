package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

class NoOpCard (
        override val id: String,
        override val name: String = "NoOp Card",
        override val text: String = "NoOp Card Text",
        override val cost: Int = 0
) : Card {

    override fun invoke(context: Context): Game {
        return context.game
    }
}
