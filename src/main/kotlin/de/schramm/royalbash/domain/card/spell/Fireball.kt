package de.schramm.royalbash.domain.card.spell

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*
import de.schramm.royalbash.domain.effect.DealDamageToPlayerEffect

class Fireball(override val id: String,
               override val instanceId: String,
               override val cost: Int): Card {

    override val name = "Fireball"
    override val text = "Deal 2 damage to your opponent."
    override val image = "Tex_fireball.PNG"
    val effect = DealDamageToPlayerEffect(2)

    override fun invoke(context: Context): Game = context.run {

        val owner = game.findPlayer(ownerId)
        val opponent = game.opponentOf(owner)

        return when {
            owner == null                     -> game.logOwnerMissing(uuidGenerator, this@Fireball)
            game.playerOnTurn != owner        -> game.logPlayerNotOnTurn(uuidGenerator, this@Fireball, owner)
            opponent == null                  -> game.logOpponentMissing(uuidGenerator, this@Fireball)
            owner.resources <= cost           -> game.logResourcesMissing(uuidGenerator, this@Fireball)
            this@Fireball !in owner.handcards -> game.logHandcardMissing(uuidGenerator, this@Fireball, owner)
            else                              -> game.updatePlayer(owner to owner.discardHandcard(this@Fireball))
                    .updatePlayer(opponent to opponent.damage(2))
                    .logDamageOnPlayer(uuidGenerator, this@Fireball, opponent, 2)
        }
    }
}
