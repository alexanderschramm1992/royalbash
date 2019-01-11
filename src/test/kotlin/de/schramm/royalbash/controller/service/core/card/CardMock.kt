package de.schramm.royalbash.controller.service.core.card

import de.schramm.royalbash.controller.service.core.Card
import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game

class CardMock(
        override val id: String,
        override val name: String = "Card Mock",
        override val text: String = "Card Mock Text",
        override val cost: Int = 0
) : Card {

    override fun invoke(context: Context): Game {
        return context.game
    }
}
