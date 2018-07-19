package de.schramm.royalbash.core.domain.card.resourcescard;

import de.schramm.royalbash.core.domain.card.effect.GrantResources;

import java.util.UUID;

public class BountifulHarvest extends AbstractResourcesCard {

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
                0
        );

        setPlayEffect(GrantResources.getInstance(3, 0, 0));
    }
}
