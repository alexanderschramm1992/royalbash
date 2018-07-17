package de.schramm.royalbash.data;

import de.schramm.royalbash.core.domain.game.board.player.Hand;
import de.schramm.royalbash.core.domain.card.summoningcard.EagerCadet;
import de.schramm.royalbash.core.domain.card.summoningcard.VeteranKnight;
import de.schramm.royalbash.core.domain.card.summoningcard.YouthfulKnight;

class HandData {

    static final Hand HAND_1 = Hand.builder()
            .card(new YouthfulKnight())
            .card(new VeteranKnight())
            .build();

    static final Hand HAND_2 = Hand.builder()
            .card(new EagerCadet())
            .build();
}
