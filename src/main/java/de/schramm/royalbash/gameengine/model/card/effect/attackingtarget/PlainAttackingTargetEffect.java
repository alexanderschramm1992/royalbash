package de.schramm.royalbash.gameengine.model.card.effect.attackingtarget;

import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import de.schramm.royalbash.gameengine.model.card.effect.attackingtarget.AttackingTargetEffect;
import lombok.val;

public class PlainAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            EffectContext context
    ) throws GameBrokenException, RuleViolationException {

        if(attackedTarget.isOccupied()) {

            val attackedSummoning = attackedTarget.getSummoning();
            attackingSummoning.dealDamage(attackedSummoning);
            attackedSummoning.defendTarget(
                    attackingSummoning,
                    attackedTarget,
                    context.getOwner(),
                    context.getGame()
            );

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
