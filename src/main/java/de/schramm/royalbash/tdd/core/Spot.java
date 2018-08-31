package de.schramm.royalbash.tdd.core;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class Spot {

    private final Card card;

    public Optional<Card> getCard() {
        return Optional.ofNullable(card);
    }

    public Spot place(Card card) {

        if(!card.canBePlacedOnSpot()) {
            return this;
        }

        return this.toBuilder()
                .card(card)
                .build();
    }
}
