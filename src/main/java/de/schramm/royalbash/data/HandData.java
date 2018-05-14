package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Hand;

import static de.schramm.royalbash.data.CardData.EAGER_RECRUIT;
import static de.schramm.royalbash.data.CardData.VETERAN_KNIGHT;
import static de.schramm.royalbash.data.CardData.YOUTHFUL_KNIGHT;

public class HandData {

    public static final Hand HAND_1 = Hand.builder()
            .card(YOUTHFUL_KNIGHT)
            .card(VETERAN_KNIGHT)
            .build();

    public static final Hand HAND_2 = Hand.builder()
            .card(EAGER_RECRUIT)
            .build();
}
