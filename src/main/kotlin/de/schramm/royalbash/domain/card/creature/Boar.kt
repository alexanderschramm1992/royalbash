package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.AttackCreatureContext
import de.schramm.royalbash.domain.AttackPlayerContext
import de.schramm.royalbash.domain.AttackSpotContext
import de.schramm.royalbash.domain.Game

data class Boar(override val id: String,
                override val instanceId: String,
                override val hitpoints: Int,
                override val attack: Int,
                override val cost: Int): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val name = "Wild Boar"
    override val text = ""
    override val image = "boar-card.png"

    override fun attack(context: AttackPlayerContext): Game {
        TODO("not implemented")
    }

    override fun attack(context: AttackCreatureContext): Game {
        TODO("not implemented")
    }

    override fun attack(context: AttackSpotContext): Game {
        TODO("not implemented")
    }

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)
}
