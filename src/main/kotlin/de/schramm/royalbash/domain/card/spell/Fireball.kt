package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logDamageOnPlayer
import de.schramm.royalbash.domain.card.logInvokationOnPlayer
import de.schramm.royalbash.domain.card.logResourcesMissing
import de.schramm.royalbash.domain.card.logTargetPlayerIsOwner

class Fireball(override val id: String,
               override val instanceId: String,
               override val cost: Int): SpellBase(id, instanceId, cost) {

    override val name = "Fireball"
    override val text = "Deal 2 damage to your opponent."
    override val image = "Tex_fireball.PNG"

    override fun invoke(context: InvokationOnPlayerContext): Game = context.run {
        when {
            owner.resources <= cost -> game.logResourcesMissing(uuidGenerator, this@Fireball)
            owner == target         -> game.logTargetPlayerIsOwner(uuidGenerator, this@Fireball, target)
            else                    -> game
                    .updatePlayer(owner to owner.discardHandcard(this@Fireball))
                    .updatePlayer(target to target.damage(2))
                    .logDamageOnPlayer(uuidGenerator, this@Fireball, target, 2)
                    .logInvokationOnPlayer(uuidGenerator, this@Fireball, target)
        }
    }
}
