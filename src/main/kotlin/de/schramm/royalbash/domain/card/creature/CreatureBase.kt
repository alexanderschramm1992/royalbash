package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

abstract class CreatureBase(override val id: String,
                            override val instanceId: String,
                            override val hitpoints: Int,
                            override val attack: Int,
                            override val cost: Int): Creature {

    override fun invoke(context: InvokationOnSpotContext): Game = context.run {
        return when {
            target.creature != null -> game.logTargetSpotOccupied(uuidGenerator, this@CreatureBase)
            owner.resources < cost  -> game.logResourcesMissing(uuidGenerator, this@CreatureBase)
            else                    -> game.updatePlayer(owner to owner
                    .reduceResourcesBy(cost)
                    .removeHandcard(this@CreatureBase)
                    .updateSpot(target to target.place(this@CreatureBase)))
                    .logInvokationOnSpot(uuidGenerator, this@CreatureBase, owner)
        }
    }

    override fun invoke(context: InvokationOnCreatureContext): Game = context.run {
        game.logCannotInvokeOnCreature(uuidGenerator, this@CreatureBase)
    }

    override fun invoke(context: InvokationOnPlayerContext): Game = context.run {
        game.logCannotInvokeOnPlayer(uuidGenerator, this@CreatureBase)
    }

    override fun attack(context: AttackPlayerContext): Game = context.run {
        game.updatePlayer(defender to defender.reduceHitpointsBy(attack))
                .log(uuidGenerator, "$name dealt $attack damage to ${defender.name}")
    }

    override fun attack(context: AttackCreatureContext): Game = context.run {
        game.updateCreature(defender to defender.reduceHitpointsBy(attack))
                .log(uuidGenerator, "$name dealt $attack damage to ${defender.name}")
                .updateCreature(this@CreatureBase to this@CreatureBase.reduceHitpointsBy(defender.attack))
                .log(uuidGenerator, "${defender.name} dealt ${defender.attack} damage to $name")
                .buryDeadCreatures(uuidGenerator)
    }

    override fun attack(context: AttackSpotContext): Game = context.run {
        game.log(uuidGenerator, "Attacking Spots not implemented yet")
    }
}
