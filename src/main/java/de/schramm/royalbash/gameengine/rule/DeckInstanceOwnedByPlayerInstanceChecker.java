package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.deck.DeckInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import org.springframework.stereotype.Component;

@Component
public class DeckInstanceOwnedByPlayerInstanceChecker {

    public void checkIfDeckInstanceIsOwnedByPlayerInstance(
            DeckInstance deckInstance,
            PlayerInstance playerInstance,
            Board board
    ) throws GameRuleViolationException {

        if(
               board.getPlayerBlueInstance().equals(playerInstance)
            && board.getPlayerBlueDeckInstance().equals(deckInstance)
        ) {

            // do nothing
        } else if(
               board.getPlayerRedInstance().equals(playerInstance)
            && board.getPlayerRedDeckInstance().equals(deckInstance)
        ) {

            // do nothing
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "DeckInstance %s is not owned by PlayerInstance %s",
                            deckInstance.getId(),
                            playerInstance.getId()
                    )
            );
        }
    }
}
