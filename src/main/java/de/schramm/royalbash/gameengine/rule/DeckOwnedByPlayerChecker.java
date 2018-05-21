package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.SummoningDeck;
import de.schramm.royalbash.model.Player;
import org.springframework.stereotype.Component;

@Component
public class DeckOwnedByPlayerChecker {

    public void check(
            SummoningDeck summoningDeck,
            Player player
    ) throws GameRuleViolationException {

        if(player.getSummoningDeck().equals(summoningDeck)) {

            // do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "SummoningDeck %s is not owned by Player %s",
                            summoningDeck.getId(),
                            player.getId()
                    )
            );
        }
    }
}
