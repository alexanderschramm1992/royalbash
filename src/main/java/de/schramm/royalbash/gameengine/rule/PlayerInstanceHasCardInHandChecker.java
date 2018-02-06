package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import org.springframework.stereotype.Component;

@Component
public class PlayerInstanceHasCardInHandChecker {

    public void checkIfPlayerInstanceHasCardInHand(
            PlayerInstance playerInstance,
            CardInstance cardInstance
    ) throws GameRuleViolationException {

        if (!playerInstance.getHandCardInstanceList().contains(cardInstance)) {

            throw new GameRuleViolationException(
                    String.format(
                            "PlayerInstance %s does not have CardInstance %s in hand",
                            playerInstance.getId(),
                            cardInstance.getId()
                    )
            );
        }
    }
}
