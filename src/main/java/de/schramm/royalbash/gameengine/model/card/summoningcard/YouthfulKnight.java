package de.schramm.royalbash.gameengine.model.card.summoningcard;

import de.schramm.royalbash.gameengine.model.Tag;
import de.schramm.royalbash.gameengine.model.card.effect.MountedAttackingTargetEffect;

import java.util.UUID;

public class YouthfulKnight extends SummoningCard {

    public YouthfulKnight() {

        super(
                UUID.fromString("c31a66c7-2f76-4e81-a922-835272833967"),
                "Youthful Knight",
                "/img/youthful_knight.png",
                "Creature",
                "Knight",
                "<b>Mounted</b>",
                "Pride goes before a fall",
                2,
                1,
                0,
                2,
                1
        );

        addTag(Tag.MOUNTED);
        this.setAttackingTargetEffect(new MountedAttackingTargetEffect());
    }
}
