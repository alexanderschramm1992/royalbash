package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.game.board.player.field.Card;
import de.schramm.royalbash.core.domain.game.board.player.field.ResourcesCard;
import de.schramm.royalbash.core.domain.game.board.player.field.SummoningCard;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Hand {

    private static final int MAX = 5;

    @Singular("card")
    private final List<Card> cards;

    Hand addCard(Card card) throws RuleViolationException {

        if(cards.size() < MAX) {
            val newCards = this.cards;
            newCards.add(card);
            return new Hand(newCards);
        } else {
            throw new RuleViolationException("Cannot draw another card");
        }
    }

    Hand removeCard(Card card) throws RuleViolationException {

        if (cards.contains(card)) {
            val newCards = new ArrayList<Card>(cards);
            newCards.remove(card);
            return new Hand(newCards);
        } else {
            throw new RuleViolationException(String.format("Card %s not in hand", card.getId()));
        }
    }

    public Optional<SummoningCard> findSummoningCard(UUID cardId) {
        return findHandCard(cardId)
                .filter(SummoningCard.class::isInstance)
                .map(SummoningCard.class::cast);
    }

    public Optional<ResourcesCard> findResourcesCard(UUID cardId) {
        return findHandCard(cardId)
                .filter(ResourcesCard.class::isInstance)
                .map(ResourcesCard.class::cast);
    }

    private Optional<Card> findHandCard(UUID cardId) {

        return cards.stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst();
    }
}
