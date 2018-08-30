package de.schramm.royalbash.tdd.core;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class Player {

    private final String name;
    private final int hitpoints;
    @Singular("handcard")
    private final List<Card> handcards;

    public Player setHitpoints(int hitpoints) {
        return Player.builder()
                .hitpoints(hitpoints)
                .build();
    }

    Player removeHandcard(Card handcard) {

        val handcards = this.handcards.stream()
                .filter(ownHandcard -> !ownHandcard.equals(handcard))
                .collect(Collectors.toList());

        return this.toBuilder()
                .clearHandcards()
                .handcards(handcards)
                .build();
    }

    boolean hasCard(Card card) {
        return handcards.stream()
                .anyMatch(handcard -> handcard.equals(card));
    }
}
