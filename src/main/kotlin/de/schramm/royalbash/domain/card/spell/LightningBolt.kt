package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*
import de.schramm.royalbash.domain.effect.DealDamageToCreatureEffect

data class LightningBolt(override val id: String,
                         override val instanceId: String,
                         override val cost: Int): Card {

    override val name = "Lightning Bolt"
    override val text = "Deal 2 damage to target creature."
    override val image = "Tex_mana_storm.PNG"
    val effect = DealDamageToCreatureEffect(2)

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val creature = game.findCreature(context.targetCreatureInstanceId)
        val creatureOwner = game.ownerOf(creature)

        return when {
            owner == null                          -> game.logOwnerMissing(uuidGenerator, this@LightningBolt)
            creatureOwner == null                  -> game.logCreatureOwnerMissing(uuidGenerator, this@LightningBolt)
            game.playerOnTurn != owner             -> game.logPlayerNotOnTurn(uuidGenerator, this@LightningBolt, owner)
            creature == null                       -> game.logTargetCreatureMissing(uuidGenerator, this@LightningBolt)
            owner.resources <= cost                -> game.logResourcesMissing(uuidGenerator, this@LightningBolt)
            this@LightningBolt !in owner.handcards -> game.logHandcardMissing(uuidGenerator, this@LightningBolt, owner)
            else                                   -> game.updatePlayer(
                    owner to owner.discardHandcard(this@LightningBolt))
                    .updateCreature(creature to creature.damage(2))
                    .logDamageOnCreature(uuidGenerator, this@LightningBolt, creature, creatureOwner, 2)
        }
    }
}
