package de.schramm.royalbash.gameengine.model.card.summoningcard;

import de.schramm.royalbash.gameengine.model.Tag;
import de.schramm.royalbash.gameengine.model.card.effect.MountedAttackSummoningEffect;
import de.schramm.royalbash.gameengine.model.card.effect.PlainPlayEffect;

import java.util.Collections;
import java.util.UUID;

public class YouthfulKnight extends SummoningCard {

    public YouthfulKnight() {

        super(
                UUID.fromString("c31a66c7-2f76-4e81-a922-835272833967"),
                "Youthful Knight",
                "/img/youthful_knight.png",
                "Creature",
                "Knight",
                "",
                "",
                2,
                1,
                0,
                3,
                2,
                Collections.singleton(Tag.MOUNTED),
                new PlainPlayEffect(),
                new MountedAttackSummoningEffect()
        );
    }
}
