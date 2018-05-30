package de.schramm.royalbash.data;

import de.schramm.royalbash.gameengine.model.Hand;
import de.schramm.royalbash.gameengine.model.card.summoningcard.EagerCadet;
import de.schramm.royalbash.gameengine.model.card.summoningcard.VeteranKnight;
import de.schramm.royalbash.gameengine.model.card.summoningcard.YouthfulKnight;

class HandData {

    static final Hand HAND_1 = Hand.builder()
            .card(new YouthfulKnight())
            .card(new VeteranKnight())
            .build();

    static final Hand HAND_2 = Hand.builder()
            .card(new EagerCadet())
            .build();
}
