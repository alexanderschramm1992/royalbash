package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class Deck {

    private UUID id;

    @Singular("card")
    private List<SummoningCard> summoningCards;

    public static Deck fromBlueprint(Blueprint blueprint, UUID id) {

        return Deck.builder()
                .id(id)
                .summoningCards(blueprint.getSummoningCards())
                .build();
    }

    public void shuffle() {

        Collections.shuffle(summoningCards);
    }

    public SummoningCard drawCard() {

        if(summoningCards.isEmpty()) {

            return null;
        } else {

            List<SummoningCard> list = new ArrayList<>(summoningCards);
            SummoningCard summoningCard = list.remove(0);

            summoningCards = list;

            return summoningCard;
        }
    }
}
