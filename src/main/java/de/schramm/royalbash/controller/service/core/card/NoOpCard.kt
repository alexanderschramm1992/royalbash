package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game

class NoOpCard (
        override val id: String,
        override val name: String = "NoOp Card",
        override val text: String = "",
        override val cost: Int = 0
) : Card {

    override fun invoke(context: Context): Game {
        return context.game
    }
}
