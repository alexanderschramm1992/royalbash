package de.schramm.royalbash.model.deck;

import de.schramm.royalbash.model.card.instance.CardInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class DeckInstance {

    private UUID id;

    @Singular("cardInstance")
    private List<CardInstance> cardInstanceList;

    public void shuffle() {

        Collections.shuffle(cardInstanceList);
    }

    public CardInstance drawCard() {

        if(cardInstanceList.isEmpty()) {

            return null;
        } else {

            List<CardInstance> list = new ArrayList<>();
            list.addAll(cardInstanceList);
            CardInstance drawnCardInstance = list.remove(0);

            cardInstanceList = list;

            return drawnCardInstance;
        }
    }
}
