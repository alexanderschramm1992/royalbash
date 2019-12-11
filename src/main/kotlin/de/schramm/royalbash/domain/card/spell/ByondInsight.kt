package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logDrawEffect
import de.schramm.royalbash.domain.card.logInvokationOnPlayer
import de.schramm.royalbash.domain.card.logResourcesMissing
import de.schramm.royalbash.domain.card.logTargetPlayerNotOwner

data class ByondInsight(
        override val id: String,
        override val instanceId: String,
        override val cost: Int): SpellBase(id, instanceId, cost) {

    override val name = "Beyond Insight"
    override val text = "Draw two cards."
    override val image = "byond-insight-card.png"

    override fun invoke(context: InvokationOnPlayerContext): Game = context.run {
        when {
            owner.resources <= cost -> game.logResourcesMissing(uuidGenerator, this@ByondInsight)
            owner == target         -> game.logTargetPlayerNotOwner(uuidGenerator, this@ByondInsight, target, owner)
            else                    -> game
                    .updatePlayer(owner to owner
                            .discardHandcard(this@ByondInsight)
                            .drawCards(2))
                    .logDrawEffect(uuidGenerator, this@ByondInsight, owner, 2)
                    .logInvokationOnPlayer(uuidGenerator, this@ByondInsight, owner)
        }
    }
}
