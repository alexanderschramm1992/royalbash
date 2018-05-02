package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.rule.PlayerHasCardInHandChecker;
import de.schramm.royalbash.gameengine.rule.PlayerOnBoardChecker;
import de.schramm.royalbash.gameengine.rule.RequiredDomainObjectChecker;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.card.CardRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SummonHandler {

    // Repositories
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    // Rules
    private final RequiredDomainObjectChecker requiredDomainObjectChecker;
    private final PlayerOnBoardChecker playerOnBoardChecker;
    private final PlayerHasCardInHandChecker playerHasCardInHandChecker;

    @Autowired
    public SummonHandler(
            CardRepository cardRepository,
            PlayerRepository playerRepository,
            BoardRepository boardRepository,
            RequiredDomainObjectChecker requiredDomainObjectChecker,
            PlayerOnBoardChecker playerOnBoardChecker,
            PlayerHasCardInHandChecker playerHasCardInHandChecker
    ) {
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
        this.boardRepository = boardRepository;
        this.requiredDomainObjectChecker = requiredDomainObjectChecker;
        this.playerOnBoardChecker = playerOnBoardChecker;
        this.playerHasCardInHandChecker = playerHasCardInHandChecker;
    }

    public Summoning summon(
            UUID boardId,
            UUID playerId,
            UUID cardId
    ) throws GameEngineException {

        // Fetch domain objects

        Board board = boardRepository.find(boardId);
        Player player = playerRepository.find(playerId);
        Card card = cardRepository.find(cardId);

        // Apply rules

        requiredDomainObjectChecker.check(
                board,
                player,
                card
        );
        playerOnBoardChecker.check(
                player,
                board
        );
        playerHasCardInHandChecker.check(
                player,
                card
        );

        // Summon Instance

        player.removeCard(card);
        Summoning summoning = Summoning.fromCard(card, UUID.randomUUID());
        player.summon(summoning);

        // Save changes

        boardRepository.save(board);
        playerRepository.save(player);
        cardRepository.save(card);

        // Return updated Board

        return summoning;
    }
}
