package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.*;
import de.schramm.royalbash.persistence.card.CardRepository;
import de.schramm.royalbash.persistence.game.GameRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import de.schramm.royalbash.persistence.target.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SummonHandler {

    // Repositories
    private final GameRepository gameRepository;
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;
    private final TargetRepository targetRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerOnBoardChecker playerOnBoardChecker;
    private final PlayerHasCardInHandChecker playerHasCardInHandChecker;

    @Autowired
    public SummonHandler(
            GameRepository gameRepository,
            CardRepository cardRepository,
            PlayerRepository playerRepository,
            TargetRepository targetRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerOnBoardChecker playerOnBoardChecker,
            PlayerHasCardInHandChecker playerHasCardInHandChecker
    ) {
        this.gameRepository = gameRepository;
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
        this.targetRepository = targetRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerOnBoardChecker = playerOnBoardChecker;
        this.playerHasCardInHandChecker = playerHasCardInHandChecker;
    }

    public Summoning summon(
            UUID gameId,
            UUID playerId,
            UUID cardId,
            UUID targetId
    ) throws GameEngineException {

        // Fetch domain objects

        Game game = gameRepository.find(gameId);
        Player player = playerRepository.find(playerId);
        Card card = cardRepository.find(cardId);
        Target target = targetRepository.find(targetId);
        // Apply rules

        requiredDomainObjectChecker.check(
                game,
                player,
                card,
                target
        );
        playerOnBoardChecker.check(
                player,
                game.getBoard()
        );
        playerHasCardInHandChecker.check(
                player,
                card
        );

        // Summon Instance

        player.removeCard(card);
        Summoning summoning = Summoning.fromCard(card, UUID.randomUUID());
        player.summon(summoning, target);

        // Save changes

        playerRepository.save(player);
        cardRepository.save(card);

        // Return updated Board

        return summoning;
    }
}
