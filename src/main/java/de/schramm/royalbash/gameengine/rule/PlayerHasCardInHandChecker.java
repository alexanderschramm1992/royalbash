package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.SummoningCard;
import de.schramm.royalbash.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerHasCardInHandChecker {

    public void check(
            Player player,
            SummoningCard summoningCard
    ) throws GameRuleViolationException {

        if (!player.getHand().getCards().contains(summoningCard)) {

            throw new GameRuleViolationException(
                    String.format(
                            "Player %s does not have SummoningCard %s in hand",
                            player.getId(),
                            summoningCard.getId()
                    )
            );
        }
    }
}
