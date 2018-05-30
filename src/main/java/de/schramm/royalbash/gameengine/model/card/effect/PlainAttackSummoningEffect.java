package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public class PlainAttackSummoningEffect implements AttackSummoningEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Summoning attackedSummoning,
            EffectContext context
    ) {

        attackedSummoning.reduceCurrentHealth(attackingSummoning.getCurrentStrength());
        attackingSummoning.reduceCurrentHealth(attackedSummoning.getCurrentStrength());

        if(attackedSummoning.getCurrentHealth() <= 0) {
            context.getGame().getBoard().bury(attackedSummoning);
        }

        if(attackingSummoning.getCurrentHealth() <= 0) {
            context.getGame().getBoard().bury(attackingSummoning);
        }
    }
}
