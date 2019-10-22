package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.effect.DrawHandcardsEffect

data class ByondInsight(
        override val id: String,
        override val instanceId: String,
        override val cost: Int
) : Card {

    override val name = "Beyond Insight"
    override val text = "Draw two cards."
    override val image = "Tex_sight.PNG"
    val effect = DrawHandcardsEffect(2)

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)

        if (owner == null) {
            return game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing owner")
        }

        if (targetSpot == null) {
            return game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing target spot")
        }

        if (owner.resources >= cost) {
            return game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing resources")
        }

        return if (owner != null) {
            val updatedPlayer = owner.drawCards(effect.amountOfCards)
            game.updatePlayer(owner to updatedPlayer)
        } else game
    }
}
