package de.schramm.royalbash.domain

fun Creature.spawnCreature(context: Context): Game {

    val game = context.game
    val owner = game.findPlayer(context.owner)
    val targetSpot = context.targetSpot

    return if (targetSpot != null && owner != null) {
        val updatedOwner = owner.updateSpot(targetSpot to targetSpot.place(this))
        game.updatePlayer(owner to updatedOwner)
    } else game
}
