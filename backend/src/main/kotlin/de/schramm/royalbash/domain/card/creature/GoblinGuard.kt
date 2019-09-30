package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.effect.DiscardHandcardsEffect
import de.schramm.royalbash.domain.spawnCreature

data class GoblinGuard(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int
                      ) : Creature {

    override val name = "Goblin Guard"
    override val text = "When Goblin Guard enters the battlefield, target player discards a card."
    override val image = "FantasyCharacters_guard_goblin_bg.png"

    val effect = DiscardHandcardsEffect(1)

    override fun invoke(context: Context): Game {

        val updatedGame = this.spawnCreature(context)

        val updatedContext = context.copy(game = updatedGame)

        return effect.invoke(updatedContext)
    }

    override fun damage(amountOfDamage: Int): Creature =
            this.copy(hitpoints = hitpoints - amountOfDamage)
}
