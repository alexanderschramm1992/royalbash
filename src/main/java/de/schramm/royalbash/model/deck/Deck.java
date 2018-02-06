package de.schramm.royalbash.model.deck;

import de.schramm.royalbash.model.card.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Deck {

    private UUID id;

    @Singular("card")
    private List<Card> cardList;
}
