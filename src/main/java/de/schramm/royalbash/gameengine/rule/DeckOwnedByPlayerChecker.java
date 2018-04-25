package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Account;
import org.springframework.stereotype.Component;

@Component
public class DeckOwnedByPlayerChecker {

    public void checkIfDeckIsOwnedByPlayer(
            Deck deck,
            Account account
    ) throws GameRuleViolationException {

        if(account.getDeckSet().contains(deck)) {

            //do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Account %s does not own Deck %s",
                            account.getId(),
                            deck.getId()
                    )
            );
        }
    }
}
