package de.schramm.royalbash.model;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Hand {

    @Singular("card")
    private List<Card> cards;

    public void addCard(Card card) {

        cards.add(card);
    }

    public void removeCard(Card card) throws GameRuleViolationException {

        if (cards.contains(card)) {

            cards.remove(card);
        } else {

            throw new GameRuleViolationException(
                    String.format(
                            "Card %s not in hand",
                            card.getId()
                    )
            );
        }
    }
}
