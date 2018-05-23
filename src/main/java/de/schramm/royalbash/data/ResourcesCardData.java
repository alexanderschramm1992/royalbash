package de.schramm.royalbash.data;

import de.schramm.royalbash.model.ResourcesCard;

import java.util.UUID;

public class ResourcesCardData {

    public static final ResourcesCard BOUNTIFUL_HARVEST = ResourcesCard.builder()
            .id(UUID.fromString("68dcbbe2-9a7c-45a4-b488-db21087c308f"))
            .name("Bountiful Harvest")
            .image("/img/bountiful_harvest.jpg")
            .type("Resources")
            .text("You gain 3 {R}")
            .costRations(0)
            .costMaterial(0)
            .costBlessing(0)
            .build();
}
