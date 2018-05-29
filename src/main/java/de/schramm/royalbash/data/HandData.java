package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Hand;
import de.schramm.royalbash.model.summoningcard.EagerCadet;
import de.schramm.royalbash.model.summoningcard.VeteranKnight;
import de.schramm.royalbash.model.summoningcard.YouthfulKnight;

public class HandData {

    public static final Hand HAND_1 = Hand.builder()
            .card(new YouthfulKnight())
            .card(new VeteranKnight())
            .build();

    public static final Hand HAND_2 = Hand.builder()
            .card(new EagerCadet())
            .build();
}
