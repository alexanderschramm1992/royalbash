package de.schramm.royalbash.gameengine.model.card.resourcescard;

import de.schramm.royalbash.gameengine.model.card.effect.GrantResources;

import java.util.UUID;

public class RuralHomestead extends ResourcesCard {

    public RuralHomestead() {
        super(
                UUID.fromString("6f6f1704-ec6e-4c93-b17a-d88fef5f55b9"),
                "Rural Homestead",
                "/img/rural_homestead.png",
                "Resources",
                "",
                "Gain <b>5 Rations</b>",
                "",
                0,
                1,
                0,
                GrantResources.getInstance(5, 0, 0)
        );
    }
}
