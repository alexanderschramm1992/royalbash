package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.*

data class GainResourcesEffect(private val amountOfResources: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game
        val actualPlayer = context.owner.let(game::findPlayer)

        return if (actualPlayer != null) {
            val updatedPlayer = actualPlayer.copy(resources = actualPlayer.resources + amountOfResources)
            game.updatePlayer(actualPlayer to updatedPlayer)
        } else game
    }
}
