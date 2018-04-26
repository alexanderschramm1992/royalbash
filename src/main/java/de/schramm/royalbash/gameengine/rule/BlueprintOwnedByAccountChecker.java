package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Blueprint;
import de.schramm.royalbash.model.Account;
import org.springframework.stereotype.Component;

@Component
public class BlueprintOwnedByAccountChecker {

    public void check(
            Blueprint blueprint,
            Account account
    ) throws GameRuleViolationException {

        if(account.getBlueprints().contains(blueprint)) {

            //do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Account %s does not own Blueprint %s",
                            account.getId(),
                            blueprint.getId()
                    )
            );
        }
    }
}
