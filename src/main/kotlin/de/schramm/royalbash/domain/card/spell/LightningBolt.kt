package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logDamageOnCreature
import de.schramm.royalbash.domain.card.logInvokationOnCreature
import de.schramm.royalbash.domain.card.logResourcesMissing

data class LightningBolt(override val id: String,
                         override val instanceId: String,
                         override val cost: Int): SpellBase(id, instanceId, cost) {

    override val name = "Lightning Bolt"
    override val text = "Deal 2 damage to target creature."
    override val image = "Tex_mana_storm.PNG"

    override fun invoke(context: InvokationOnCreatureContext): Game = context.run {
        when {
            owner.resources <= cost -> game.logResourcesMissing(uuidGenerator, this@LightningBolt)
            else                    -> game
                    .updatePlayer(owner to owner.discardHandcard(this@LightningBolt))
                    .updateCreature(target to target.reduceHitpointsBy(2))
                    .logDamageOnCreature(uuidGenerator, this@LightningBolt, target, 2)
                    .buryDeadCreatures(uuidGenerator)
                    .logInvokationOnCreature(uuidGenerator, this@LightningBolt, target)
        }
    }
}
