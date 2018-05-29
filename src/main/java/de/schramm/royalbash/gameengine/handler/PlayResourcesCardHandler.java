package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.model.resourcescard.ResourcesCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayResourcesCardHandler {

    private final PlayerOnBoardChecker playerOnBoardChecker;
    private final PlayerHasCardInHandChecker playerHasCardInHandChecker;

    @Autowired
    public PlayResourcesCardHandler(
            PlayerOnBoardChecker playerOnBoardChecker,
            PlayerHasCardInHandChecker playerHasCardInHandChecker
    ) {
        this.playerOnBoardChecker = playerOnBoardChecker;
        this.playerHasCardInHandChecker = playerHasCardInHandChecker;
    }

    public Game play(
            Game game,
            Player player,
            ResourcesCard resourcesCard
    ) throws GameEngineException {

        playerOnBoardChecker.check(
                player,
                game.getBoard()
        );

        playerHasCardInHandChecker.check(
                player,
                resourcesCard
        );

        player.getHand().removeCard(resourcesCard);
        resourcesCard.apply(player.getResourcePool());

        return game;
    }
}
