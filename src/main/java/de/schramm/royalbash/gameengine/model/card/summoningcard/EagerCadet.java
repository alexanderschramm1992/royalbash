package de.schramm.royalbash.gameengine.model.card.summoningcard;

import java.util.UUID;

public class EagerCadet extends SummoningCard {

    public EagerCadet() {

        super(
                UUID.fromString("fafd0e46-f4ee-4406-b540-b049c26d5f77"),
                "Eager Cadet",
                "/img/eager_cadet.png",
                "Creature",
                "Knight",
                "",
                "No one can hold me back!",
                1,
                0,
                0,
                1,
                1
        );
    }
}
