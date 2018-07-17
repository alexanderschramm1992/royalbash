package de.schramm.royalbash.data;

import de.schramm.royalbash.core.domain.game.board.player.SummoningDeck;
import de.schramm.royalbash.core.domain.card.summoningcard.*;

import java.util.UUID;

class SummoningDeckData {

    static final SummoningDeck SUMMONING_DECK_1 = SummoningDeck.builder()
            .id(UUID.fromString("b68ba1e2-c8bf-470d-8172-ef1632482fe8"))
            .card(new PlatedTuskrider())
            .card(new ProvenCombatant())
            .card(new BattlefieldMedic())
            .card(new EagerCadet())
            .build();

    static final SummoningDeck SUMMONING_DECK_2 = SummoningDeck.builder()
            .id(UUID.fromString("924a99ad-01af-4226-955d-b56b792ee01e"))
            .card(new YouthfulKnight())
            .build();
}
