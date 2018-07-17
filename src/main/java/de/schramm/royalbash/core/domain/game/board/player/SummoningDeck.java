package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.card.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class SummoningDeck {

    private final UUID id;

    @Singular("card")
    private List<SummoningCard> summoningCards;

    public void shuffle() {

        Collections.shuffle(summoningCards);
    }

    SummoningCard drawCard() {

        return summoningCards.isEmpty() ? null : summoningCards.remove(0);
    }
}
