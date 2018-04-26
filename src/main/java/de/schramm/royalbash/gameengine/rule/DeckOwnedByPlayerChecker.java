package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Deck;
import de.schramm.royalbash.model.Player;
import org.springframework.stereotype.Component;

@Component
public class DeckOwnedByPlayerChecker {

    public void check(
            Deck deck,
            Player player
    ) throws GameRuleViolationException {

        if(player.getDeck().equals(deck)) {

            // do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Deck %s is not owned by Player %s",
                            deck.getId(),
                            player.getId()
                    )
            );
        }
    }
}
