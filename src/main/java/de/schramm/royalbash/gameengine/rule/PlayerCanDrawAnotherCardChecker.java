package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlayerCanDrawAnotherCardChecker {

    private final int maxHandCards;

    public PlayerCanDrawAnotherCardChecker(
            @Value("${gameengine.rules.maxhandcards}")
                    int maxHandCards
    ) {
        this.maxHandCards = maxHandCards;
    }

    public void checkIfPlayerInstanceCanDrawAnotherCard(
            Player player
    ) throws GameRuleViolationException {

        if (player.getCards().size() >= maxHandCards) {

            throw new GameRuleViolationException(
                    String.format(
                            "Player %s cannot draw another card (%d hand cardModels)",
                            player.getId(),
                            player.getCards().size()
                    )
            );
        }
    }
}
