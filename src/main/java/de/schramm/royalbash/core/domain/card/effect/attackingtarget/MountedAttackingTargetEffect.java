package de.schramm.royalbash.core.domain.card.effect.attackingtarget;

import de.schramm.royalbash.core.domain.card.EffectContext;
import de.schramm.royalbash.core.domain.card.summoningcard.Tag;
import de.schramm.royalbash.core.exception.GameEngineException;
import lombok.val;

import java.util.UUID;

public class MountedAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            EffectContext context
    ) throws GameEngineException {

        val attackingSummoning = context.getOwner().findSummoning(attackingSummoningId);
        val attackedTarget = context.getGame().findOpponent(context.getOwner()).findTarget(attackedTargetId);

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            if(attackingSummoning.hasTag(Tag.MOUNTED)) {
                new PlainAttackingTargetEffect().apply(
                        attackingSummoningId,
                        attackedTargetId,
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
