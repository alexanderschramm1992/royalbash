package de.schramm.royalbash.gameengine.model.card.resourcescard;

import de.schramm.royalbash.gameengine.model.card.effect.GrantResources;

import java.util.UUID;

public class BountifulHarvest extends ResourcesCard {

    public BountifulHarvest() {

        super(
                UUID.fromString("68dcbbe2-9a7c-45a4-b488-db21087c308f"),
                "Bountiful Harvest",
                "/img/bountiful_harvest.jpg",
                "Resources",
                "",
                "Gain <b>3 Rations</b>",
                "",
                0,
                0,
                0,
                GrantResources.getInstance(3, 0, 0)
        );
    }
}
