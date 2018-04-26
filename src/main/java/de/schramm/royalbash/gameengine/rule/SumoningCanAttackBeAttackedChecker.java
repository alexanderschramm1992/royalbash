package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.AttackableCanAttack;
import de.schramm.royalbash.model.Summoning;
import org.springframework.stereotype.Component;

@Component
public class SumoningCanAttackBeAttackedChecker {

    public void checkIfInstanceCanAttack(
            Summoning attackingSummoning
    ) throws GameRuleViolationException {

        if (attackingSummoning.getClass().isAssignableFrom(AttackableCanAttack.class)) {

            throw new GameRuleViolationException(
                    String.format(
                            "Summoning %s cannot attack",
                            attackingSummoning.getId()
                    )
            );
        }
    }

    public void checkIfInstanceIsAttackable(
            Summoning attackedSummoning
    ) throws GameRuleViolationException {

        if (attackedSummoning.getClass().isAssignableFrom(AttackableCanAttack.class)) {

            throw new GameRuleViolationException(
                    String.format(
                            "Summoning %s cannot attack",
                            attackedSummoning.getId()
                    )
            );
        }
    }
}
