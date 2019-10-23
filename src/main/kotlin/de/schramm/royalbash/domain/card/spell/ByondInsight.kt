package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class ByondInsight(
        override val id: String,
        override val instanceId: String,
        override val cost: Int): Card {

    override val name = "Beyond Insight"
    override val text = "Draw two cards."
    override val image = "Tex_sight.PNG"

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)

        return when {
            owner == null                         -> game.logOwnerMissing(uuidGenerator, this@ByondInsight)
            game.playerOnTurn != owner            -> game.logPlayerNotOnTurn(uuidGenerator, this@ByondInsight, owner)
            owner.resources <= cost               -> game.logResourcesMissing(uuidGenerator, this@ByondInsight)
            this@ByondInsight !in owner.handcards -> game.logHandcardMissing(uuidGenerator, this@ByondInsight, owner)
            else                                  -> game.updatePlayer(owner to owner
                    .discardHandcard(this@ByondInsight)
                    .drawCards(2))
                    .logDrawEffect(uuidGenerator, this@ByondInsight, owner, 2)
        }
    }
}
