package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Deck {

    private UUID id;

    @Singular("card")
    private List<Card> cards;

    public static Deck fromBlueprint(Blueprint blueprint, UUID id) {

        return Deck.builder()
                .id(id)
                .cards(blueprint.getCards())
                .build();
    }

    public void shuffle() {

        Collections.shuffle(cards);
    }

    public Card drawCard() {

        if(cards.isEmpty()) {

            return null;
        } else {

            List<Card> list = new ArrayList<>(cards);
            Card card = list.remove(0);

            cards = list;

            return card;
        }
    }
}
