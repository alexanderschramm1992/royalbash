package de.schramm.royalbash.domain

fun Creature.spawnCreature(context: Context): Game? {

    val game = context.game
    val owner = game.findPlayer(context.ownerId)
    val targetSpot = game.findSpot(context.targetSpotId)

    return if (targetSpot != null && owner != null && owner.resources >= this.cost) {
        game.updatePlayer(owner to owner
                .copy(resources = owner.resources - cost)
                .updateSpot(targetSpot to targetSpot.place(this)))
    } else null
}
