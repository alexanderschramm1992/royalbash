package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SummonHandler {

    private final PlayerOnBoardChecker playerOnBoardChecker;
    private final PlayerHasCardInHandChecker playerHasCardInHandChecker;

    @Autowired
    public SummonHandler(
            PlayerOnBoardChecker playerOnBoardChecker,
            PlayerHasCardInHandChecker playerHasCardInHandChecker
    ) {
        this.playerOnBoardChecker = playerOnBoardChecker;
        this.playerHasCardInHandChecker = playerHasCardInHandChecker;
    }

    public Game summon(
            Game game,
            Player player,
            SummoningCard summoningCard,
            Target target
    ) throws GameEngineException {

        playerOnBoardChecker.check(
                player,
                game.getBoard()
        );

        playerHasCardInHandChecker.check(
                player,
                summoningCard
        );

        player.getHand().removeCard(summoningCard);
        Summoning summoning = Summoning.fromCard(summoningCard, UUID.randomUUID());
        player.summon(summoning, target);

        return game;
    }
}
