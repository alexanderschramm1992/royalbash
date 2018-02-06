package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Player;
import org.springframework.stereotype.Component;

@Component
public class DeckOwnedByPlayerChecker {

    public void checkIfDeckIsOwnedByPlayer(
            Deck deck,
            Player player
    ) throws GameRuleViolationException {

        if(player.getDeckSet().contains(deck)) {

            //do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Player %s does not own Deck %s",
                            player.getId(),
                            deck.getId()
                    )
            );
        }
    }
}
