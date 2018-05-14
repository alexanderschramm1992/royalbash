package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerCanDrawAnotherCardChecker;
import de.schramm.royalbash.model.SummoningCard;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrawHandler {

    private final PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker;

    @Autowired
    public DrawHandler(
            PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker
    ) {
        this.playerCanDrawAnotherCardChecker = playerCanDrawAnotherCardChecker;
    }

    public Game draw(
            Game game,
            Player player
    ) throws GameEngineException {

        playerCanDrawAnotherCardChecker.checkIfPlayerInstanceCanDrawAnotherCard(player);

        SummoningCard summoningCard = player.getSummoningDeck().drawCard();
        player.getHand().addCard(summoningCard);

        return game;
    }
}
