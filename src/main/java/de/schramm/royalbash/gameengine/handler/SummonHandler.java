package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.game.GameFinder;
import de.schramm.royalbash.persistence.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SummonHandler {

    private final GameFinder gameRepository;

    private final PlayerOnBoardChecker playerOnBoardChecker;
    private final PlayerHasCardInHandChecker playerHasCardInHandChecker;

    @Autowired
    public SummonHandler(
            GameRepository gameRepository,
            PlayerOnBoardChecker playerOnBoardChecker,
            PlayerHasCardInHandChecker playerHasCardInHandChecker
    ) {
        this.gameRepository = gameRepository;
        this.playerOnBoardChecker = playerOnBoardChecker;
        this.playerHasCardInHandChecker = playerHasCardInHandChecker;
    }

    public Game summon(
            UUID gameId,
            UUID playerId,
            UUID cardId,
            UUID targetId
    ) throws GameEngineException {

        Game game = Optional.of(
                gameRepository.findOne(gameId)
        ).orElseThrow(
                () -> new DomainObjectDoesNotExistException(
                        String.format(
                                "Game %s not found",
                                gameId
                        )
                )
        );

        Player player = game.findPlayer(playerId);
        Card card = game.findHandCard(cardId);
        Target target = game.findTarget(targetId);

        playerOnBoardChecker.check(
                player,
                game.getBoard()
        );

        playerHasCardInHandChecker.check(
                player,
                card
        );

        player.removeCard(card);
        Summoning summoning = Summoning.fromCard(card, UUID.randomUUID());
        player.summon(summoning, target);

        gameRepository.save(game);

        return game;
    }
}
