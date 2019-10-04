package de.schramm.royalbash.domain

fun Creature.spawnCreature(context: Context): Context {

    val game = context.game
    val owner = game.findPlayer(context.owner)
    val targetSpot = context.targetSpot

    return if (targetSpot != null && owner != null && owner.resources >= this.cost) {
        val updatedOwner = owner.copy(resources = owner.resources - this.cost)
                .updateSpot(targetSpot to targetSpot.place(this))
        context.copy(owner = updatedOwner, game = game.updatePlayer(owner to updatedOwner))
    } else context
}
