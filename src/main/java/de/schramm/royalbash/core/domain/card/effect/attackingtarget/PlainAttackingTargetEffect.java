package de.schramm.royalbash.core.domain.card.effect.attackingtarget;

import de.schramm.royalbash.core.domain.card.EffectContext;
import de.schramm.royalbash.core.exception.GameEngineException;
import lombok.val;

import java.util.UUID;

public class PlainAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            EffectContext context
    ) throws GameEngineException {

        val attackingSummoning = context.getOwner().findSummoning(attackingSummoningId);
        val attackedTarget = context.getEnemy().findTarget(attackedTargetId);

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
                context.getGame().getBoard().findOpponent(context.getOwner()).bury(attackedSummoning);
            }

            if(attackingSummoning.isDead()) {
                context.getOwner().bury(attackingSummoning);
            }
        }
    }
}
