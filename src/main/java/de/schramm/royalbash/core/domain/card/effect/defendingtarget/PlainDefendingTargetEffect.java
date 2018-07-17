package de.schramm.royalbash.core.domain.card.effect.defendingtarget;

import de.schramm.royalbash.core.domain.card.EffectContext;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.val;

import java.util.UUID;

public class PlainDefendingTargetEffect implements DefendingTargetEffect {

    @Override
    public void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            UUID defendingSummoningId,
            EffectContext context
    ) throws GameEngineException {

        val attackingSummoning = context.getEnemy().findSummoning(attackingSummoningId);
        val attackedTarget = context.getOwner().findTarget(attackedTargetId);
        val defendingSummoning = context.getOwner().findSummoning(defendingSummoningId);

        if(!attackedTarget.isOccupied() || !attackedTarget.getSummoning().equals(defendingSummoning)) {
            throw new RuleViolationException(String.format(
                    "Defending summoning %s does not occupy attacked target %s",
                    defendingSummoning.getId(),
                    attackedTarget.getId()
            ));
        }

        defendingSummoning.dealDamage(attackingSummoning);
    }
}
