package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Tag;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import lombok.val;

public class MountedAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            EffectContext context
    ) {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            if(attackedSummoning.getTags().contains(Tag.MOUNTED)) {
                new PlainAttackingTargetEffect().apply(
                        attackingSummoning,
                        attackedTarget,
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
}
