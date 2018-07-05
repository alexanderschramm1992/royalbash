package de.schramm.royalbash.gameengine.model.card.effect.attackingtarget;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
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
    ) throws GameEngineException {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            if(attackedSummoning.hasTag(Tag.MOUNTED)) {
                new PlainAttackingTargetEffect().apply(
                        attackingSummoning,
                        attackedTarget,
                        context
                );
            } else {
                attackingSummoning.dealDamage(attackedSummoning);
                if(attackedSummoning.isDead()) {
                    context.getGame().getBoard().findOpponent(context.getOwner()).bury(attackedSummoning);
                } else {
                    attackedSummoning.defendTarget(
                            attackingSummoning,
                            attackedTarget,
                            context.getOwner(),
                            context.getGame()
                    );

                    if(attackingSummoning.isDead()) {
                        context.getOwner().bury(attackingSummoning);
                    }
                }
            }
        }
    }
}
