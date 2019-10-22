package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*

data class Boar(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int) : Creature {

    override val name = "Wild Boar"
    override val text = ""
    override val image = "FantasyCharacters_boar_bg.png"

    override fun damage(amountOfDamage: Int) =
            this.copy(hitpoints = hitpoints - amountOfDamage)

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val targetSpot = game.findSpot(targetSpotId)

        return when {
            owner == null ->  game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing owner")
            targetSpot == null -> game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing target spot")
            owner.resources >= cost -> game.log(uuidGenerator.generateId(), "Cannot invoke Boar due to missing resources")
            else -> game.updatePlayer(owner to owner
                .copy(resources = owner.resources - cost)
                .updateSpot(targetSpot to targetSpot.place(this@Boar)))
                    .log(uuidGenerator.generateId(), "Invoked Boar on ${owner.name}'s spot")
        }
    }
}
