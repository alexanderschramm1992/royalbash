package de.schramm.royalbash.gameengine.model.card.effect.attackingtarget;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import lombok.val;

public class VigorousAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            EffectContext context
    ) throws GameEngineException {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            attackingSummoning.increaseCurrentStrength(1);
            attackingSummoning.dealDamage(attackedSummoning);
            attackingSummoning.reduceCurrentStrength(1);

            attackedSummoning.defendTarget(
                    attackingSummoning,
                    attackedTarget,
                    context.getOwner(),
                    context.getGame()
            );

            if(attackedSummoning.isDead()) {
                context.getGame().getBoard().findOpponent(context.getOwner()).bury(attackedSummoning);
            }
            if(attackingSummoning.isDead()) {
                context.getOwner().bury(attackingSummoning);
            }
        }
    }
}
