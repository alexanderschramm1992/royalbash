package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.exception.GameEngineException;
import lombok.val;

public class PlainAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws GameEngineException {

        if(attackedTarget.isOccupied()) {

            val attackedSummoning = attackedTarget.getSummoning();
            attackingSummoning.dealDamage(attackedSummoning);
            attackedSummoning.defendTarget(
                    attackingSummoning,
                    attackedTarget
            );

            attackingSummoning.setCanAttack(false);
        }
    }
}
