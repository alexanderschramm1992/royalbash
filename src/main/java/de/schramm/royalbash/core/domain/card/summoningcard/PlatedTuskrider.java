package de.schramm.royalbash.core.domain.card.summoningcard;

import java.util.UUID;

public class PlatedTuskrider extends SummoningCard {

    public PlatedTuskrider() {
        super(
                UUID.fromString("e464fec1-8a5d-48b9-8e49-3c2a4fb9b4fe"),
                "Plated Tuskrider",
                "/img/plated_tuskrider.png",
                "Creature",
                "Knight",
                "<b>Mounted</b><br><b>Vigorous</b>",
                "Tusk or lance, how will you end?",
                4,
                2,
                0,
                4,
                4
        );
    }
}
