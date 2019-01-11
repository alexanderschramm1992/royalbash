package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Creature
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.effect.DiscardHandcardsEffect

data class GoblinRaider(
        override val id: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int
) : Creature {

    override val name: String = "Goblin Raider"
    override val text = "When Goblin Raider enters the battlefield, target player discards a card."

    val effect = DiscardHandcardsEffect(1)

    override fun invoke(context: Context): Game {

        val updatedGame = CreatureUtil.spawnCreature(this, context);

        val updatedContext = context.copy(game = updatedGame)

        return effect.invoke(updatedContext)
    }

    override fun damage(amountOfDamage: Int): Creature {
        return this.copy(hitpoints = hitpoints - amountOfDamage)
    }
}
