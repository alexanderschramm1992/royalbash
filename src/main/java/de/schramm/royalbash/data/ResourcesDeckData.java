package de.schramm.royalbash.data;

import de.schramm.royalbash.model.ResourcesDeck;

import java.util.UUID;

public class ResourcesDeckData {

    public static final ResourcesDeck RESOURCES_DECK_1 = ResourcesDeck.builder()
            .id(UUID.fromString("e990e5de-723d-42b4-b799-e4b99070f83b"))
            .build();

    public static final ResourcesDeck RESOURCES_DECK_2 = ResourcesDeck.builder()
            .id(UUID.fromString("eca301fb-a7f3-4e9c-b538-37bbca79dc57"))
            .build();
}
