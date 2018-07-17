package de.schramm.royalbash.data;

import de.schramm.royalbash.core.domain.Blueprint;
import de.schramm.royalbash.core.domain.card.summoningcard.VeteranKnight;
import de.schramm.royalbash.core.domain.card.summoningcard.YouthfulKnight;

import java.util.UUID;

class BlueprintData {

    private static final Blueprint BLUEPRINT_1 = Blueprint.builder()
            .id(UUID.fromString("c31a66c7-2f76-4e81-a922-835238533967"))
            .card(new YouthfulKnight())
            .build();

    private static final Blueprint BLUEPRINT_2 = Blueprint.builder()
            .id(UUID.fromString("c31a66c7-2f76-5f92-a922-835238533967"))
            .card(new VeteranKnight())
            .build();
}
