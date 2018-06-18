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
            attackingSummoning.dealDamage(attackedSummoning);
            attackedSummoning.dealDamage(attackingSummoning);

            attackingSummoning.setCanAttack(false);

            if(attackedSummoning.isDead()) {
                context.getGame().getBoard().bury(attackedSummoning);
            }

            if(attackingSummoning.isDead()) {
                context.getGame().getBoard().bury(attackingSummoning);
            }
        }
    }
}
