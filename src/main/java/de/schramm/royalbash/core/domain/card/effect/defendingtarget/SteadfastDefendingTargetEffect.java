package de.schramm.royalbash.core.domain.card.effect.defendingtarget;

import de.schramm.royalbash.core.domain.card.EffectContext;
import de.schramm.royalbash.core.exception.GameEngineException;
import lombok.val;

import java.util.UUID;

public class SteadfastDefendingTargetEffect implements DefendingTargetEffect {
    @Override
    public void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            UUID defendingSummoningId,
            EffectContext context
    ) throws GameEngineException {

        val defendingSummoning = context.getOwner().findSummoning(defendingSummoningId);

        defendingSummoning.increaseCurrentStrength(1);
        new PlainDefendingTargetEffect().apply(
                attackingSummoningId,
                attackedTargetId,
                defendingSummoningId,
                context
        );
        defendingSummoning.reduceCurrentStrength(1);
    }
}
