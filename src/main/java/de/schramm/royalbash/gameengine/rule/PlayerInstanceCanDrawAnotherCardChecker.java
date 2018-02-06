package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.player.PlayerInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlayerInstanceCanDrawAnotherCardChecker {

    private final int maxHandCards;

    public PlayerInstanceCanDrawAnotherCardChecker(
            @Value("${gameengine.rules.maxhandcards}")
                    int maxHandCards
    ) {
        this.maxHandCards = maxHandCards;
    }

    public void checkIfPlayerInstanceCanDrawAnotherCard(
            PlayerInstance playerInstance
    ) throws GameRuleViolationException {

        if (playerInstance.getHandCardInstanceList().size() >= maxHandCards) {

            throw new GameRuleViolationException(
                    String.format(
                            "PlayerInstance %s cannot draw another card (%d hand cards)",
                            playerInstance.getId(),
                            playerInstance.getHandCardInstanceList().size()
                    )
            );
        }
    }
}
