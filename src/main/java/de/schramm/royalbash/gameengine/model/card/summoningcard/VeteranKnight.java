package de.schramm.royalbash.gameengine.model.card.summoningcard;

import java.util.UUID;

public class VeteranKnight extends SummoningCard {

    public VeteranKnight() {

        super(
                UUID.fromString("5d10c3a2-78e5-4463-85e0-57e279cac82c"),
                "Veteran Knight",
                "/img/veteran_knight.png",
                "Creature",
                "Knight",
                "",
                "I've seen too much to fear my enemy",
                4,
                1,
                0,
                3,
                4
        );
    }
}
