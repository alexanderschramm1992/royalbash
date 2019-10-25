package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logCannotInvokeOnCreature
import de.schramm.royalbash.domain.card.logCannotInvokeOnPlayer
import de.schramm.royalbash.domain.card.logCannotInvokeOnSpot

abstract class SpellBase(override val id: String,
                         override val instanceId: String,
                         override val cost: Int): Card {

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        game.logCannotInvokeOnSpot(uuidGenerator, this@SpellBase)
    }

    override fun invoke(context: InvokationOnPlayerContext): Game = context.run {
        game.logCannotInvokeOnPlayer(uuidGenerator, this@SpellBase)
    }

    override fun invoke(context: InvokationOnCreatureContext): Game = context.run {
        game.logCannotInvokeOnCreature(uuidGenerator, this@SpellBase)
    }
}
