package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class GoblinGuard(
        override val id: String,
        override val instanceId: String,
        override val hitpoints: Int,
        override val attack: Int,
        override val cost: Int): CreatureBase(id, instanceId, hitpoints, attack, cost) {

    override val name = "Goblin Guard"
    override val text = "When Goblin Guard is invoked, your opponent discards a card."
    override val image = "FantasyCharacters_guard_goblin_bg.png"

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {

        val opponent = game.opponentOf(owner)

        when {
            opponent == null        -> game.logOpponentMissing(uuidGenerator, this@GoblinGuard)
            target.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@GoblinGuard)
            owner.resources < cost  -> game.logResourcesMissing(uuidGenerator, this@GoblinGuard)
            else                    -> game
                    .updatePlayer(owner to owner
                            .reduceResourcesBy(cost)
                            .removeHandcard(this@GoblinGuard)
                            .updateSpot(target to target.place(this@GoblinGuard)))
                    .logInvokationOnSpot(uuidGenerator, this@GoblinGuard, owner)
                    .updatePlayer(opponent.id) { it.discardCards(1) }
                    .logDiscardEffect(uuidGenerator, this@GoblinGuard, opponent, 1)
        }
    }

    override fun reduceHitpointsBy(amountOfDamage: Int) = copy(hitpoints = hitpoints - amountOfDamage)
}
