package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logCannotInvokeOnPlayer
import de.schramm.royalbash.domain.card.logInvokationOnCreature
import de.schramm.royalbash.domain.card.logInvokationOnSpot
import de.schramm.royalbash.domain.card.logResourcesMissing

data class NoOpSpell(override val id: String,
                     override val instanceId: String,
                     override val cost: Int = 0): SpellBase(id, instanceId, cost) {

    override val name = "NoOpSpell"
    override val text = "NoOp Spell Text"
    override val image = "NoOp Image URL"

    override fun invoke(context: InvokationOnCreatureContext): Game = context.run {
        when {
            owner.resources < cost -> game.logResourcesMissing(uuidGenerator, this@NoOpSpell)
            else                   -> game.updatePlayer(owner to owner.discardHandcard(this@NoOpSpell))
                    .logInvokationOnCreature(uuidGenerator, this@NoOpSpell, target)
        }
    }

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        when {
            owner.resources < cost -> game.logResourcesMissing(uuidGenerator, this@NoOpSpell)
            else                   -> game.updatePlayer(owner to owner.discardHandcard(this@NoOpSpell))
                    .logInvokationOnSpot(uuidGenerator, this@NoOpSpell, owner)
        }
    }

    override fun invoke(context: InvokationOnPlayerContext): Game = context.run {
        when {
            owner.resources < cost -> game.logResourcesMissing(uuidGenerator, this@NoOpSpell)
            else                   -> game.updatePlayer(owner to owner.discardHandcard(this@NoOpSpell))
                    .logCannotInvokeOnPlayer(uuidGenerator, this@NoOpSpell)
        }
    }
}
