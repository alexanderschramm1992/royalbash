package de.schramm.royalbash.gameengine.model.card.summoningcard;

import de.schramm.royalbash.gameengine.model.card.effect.PlainAttackSummoningEffect;
import de.schramm.royalbash.gameengine.model.card.effect.PlainPlayEffect;

import java.util.Collections;
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
                "",
                4,
                1,
                0,
                3,
                4,
                Collections.emptySet(),
                new PlainPlayEffect(),
                new PlainAttackSummoningEffect()
        );
    }
}
