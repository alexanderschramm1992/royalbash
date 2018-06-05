package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Tag;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public class MountedAttackSummoningEffect implements AttackSummoningEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Summoning attackedSummoning,
            EffectContext context
    ) {

        if(attackedSummoning.getTags().contains(Tag.MOUNTED)) {
            new PlainAttackSummoningEffect().apply(
                    attackingSummoning,
                    attackedSummoning,
                    context
            );
        } else {
            attackedSummoning.reduceCurrentHealth(
                    attackingSummoning.getCurrentStrength() + 1
            );
            attackingSummoning.reduceCurrentHealth(
                    attackedSummoning.getCurrentStrength() > 0 ? attackedSummoning.getCurrentStrength() - 1 : 0
            );

            if(attackedSummoning.getCurrentHealth() <= 0) {
                context.getGame().getBoard().bury(attackedSummoning);
            }
            if(attackingSummoning.getCurrentHealth() <= 0) {
                context.getGame().getBoard().bury(attackingSummoning);
            }
        }
    }
}
