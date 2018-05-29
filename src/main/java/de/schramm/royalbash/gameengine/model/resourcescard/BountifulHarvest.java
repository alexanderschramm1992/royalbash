package de.schramm.royalbash.gameengine.model.resourcescard;

import de.schramm.royalbash.gameengine.model.ResourcePool;

import java.util.UUID;

public class BountifulHarvest extends ResourcesCard {

    public BountifulHarvest() {

        super(
                UUID.fromString("68dcbbe2-9a7c-45a4-b488-db21087c308f"),
                "Bountiful Harvest",
                "/img/bountiful_harvest.jpg",
                "Resources",
                "",
                "You gain 3 {R}",
                "",
                0,
                0,
                0
        );
    }

    @Override
    public void apply(ResourcePool resourcePool) {

        resourcePool.addRations(3);
    }
}
