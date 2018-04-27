package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerCanDrawAnotherCardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DrawHandler {

    // Repositories
    private final PlayerRepository playerRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker;

    @Autowired
    public DrawHandler(
            PlayerRepository playerRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerCanDrawAnotherCardChecker playerCanDrawAnotherCardChecker
    ) {
        this.playerRepository = playerRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerCanDrawAnotherCardChecker = playerCanDrawAnotherCardChecker;
    }

    public Card drawCard(
            UUID playerId
    ) throws GameEngineException {

        // Fetch domain objects

        Player player = playerRepository.find(playerId);

        // Apply rules

        requiredDomainObjectChecker.check(player);
        playerCanDrawAnotherCardChecker.checkIfPlayerInstanceCanDrawAnotherCard(player);

        // Draw Card and put it in hand of Player

        Card card = player.getDeck().drawCard();
        player.addCard(card);
        playerRepository.save(player);

        // Return Card

        return card;
    }
}
