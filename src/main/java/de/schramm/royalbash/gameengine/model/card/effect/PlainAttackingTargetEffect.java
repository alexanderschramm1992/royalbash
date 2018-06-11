package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import lombok.val;

public class PlainAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            EffectContext context
    ) {

        if(attackedTarget.isOccupied()) {

            val attackedSummoning = attackedTarget.getSummoning();
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
}
