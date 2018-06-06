package de.schramm.royalbash.gameengine.model.card.summoningcard;

import de.schramm.royalbash.gameengine.model.card.effect.HealRandomAlly;

import java.util.UUID;

public class BattlefieldMedic extends SummoningCard {
    public BattlefieldMedic() {
        super(
                UUID.fromString("b7cec0ea-61d1-408c-996c-c09837ea2b8f"),
                "Battlefield Medic",
                "/img/battlefield_medic.png",
                "Creature",
                "Soldier",
                "",
                "",
                2,
                0,
                0,
                1,
                1
        );

        setOwnerTurnStartEffectWithSource(HealRandomAlly.getInstance(1));
    }
}
