package de.schramm.royalbash.domain.card

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

class CardMock(
        override val id: String,
        override val name: String = "Card Mock",
        override val text: String = "Card Mock Text",
        override val image: String = "Card Image URL",
        override val cost: Int = 0
) : Card {

    override fun invoke(context: Context): Game {
        return context.game
    }
}
