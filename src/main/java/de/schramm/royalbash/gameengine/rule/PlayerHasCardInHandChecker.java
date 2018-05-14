package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerHasCardInHandChecker {

    public void check(
            Player player,
            Card card
    ) throws GameRuleViolationException {

        if (!player.getHand().getCards().contains(card)) {

            throw new GameRuleViolationException(
                    String.format(
                            "Player %s does not have Card %s in hand",
                            player.getId(),
                            card.getId()
                    )
            );
        }
    }
}
