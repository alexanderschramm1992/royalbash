package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.game.board.player.field.SummoningCard;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
@ToString
public class SummoningDeck {

    private final UUID id;

    @Singular("card")
    private final List<SummoningCard> summoningCards;

    SummoningDeck shuffle() {

        val summoningCards = this.summoningCards;
        Collections.shuffle(summoningCards);
        return new SummoningDeck(id, summoningCards);
    }

    Optional<SummoningCard> getTopCard() {
        return summoningCards.isEmpty() ? Optional.empty() : Optional.of(summoningCards.get(0));
    }

    SummoningDeck removeTopCard() {

        val summoningCards = this.summoningCards;
        summoningCards.remove(0);
        return new SummoningDeck(id, summoningCards);
    }
}
