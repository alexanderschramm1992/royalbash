package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.card.instance.AttackableCanAttack;
import de.schramm.royalbash.model.card.instance.CardInstance;
import org.springframework.stereotype.Component;

@Component
public class InstanceCanAttackBeAttackedChecker {

    public void checkIfInstanceCanAttack(
            CardInstance attackingInstance
    ) throws GameRuleViolationException {

        if (attackingInstance.getClass().isAssignableFrom(AttackableCanAttack.class)) {

            throw new GameRuleViolationException(
                    String.format(
                            "CardInstance %s cannot attack",
                            attackingInstance.getId()
                    )
            );
        }
    }

    public void checkIfInstanceIsAttackable(
            CardInstance attackedInstance
    ) throws GameRuleViolationException {

        if (attackedInstance.getClass().isAssignableFrom(AttackableCanAttack.class)) {

            throw new GameRuleViolationException(
                    String.format(
                            "CardInstance %s cannot attack",
                            attackedInstance.getId()
                    )
            );
        }
    }
}
