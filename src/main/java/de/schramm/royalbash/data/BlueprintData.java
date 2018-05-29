package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Blueprint;
import de.schramm.royalbash.model.summoningcard.VeteranKnight;
import de.schramm.royalbash.model.summoningcard.YouthfulKnight;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BlueprintData {

    public static final Blueprint BLUEPRINT_1 = Blueprint.builder()
            .id(UUID.fromString("c31a66c7-2f76-4e81-a922-835238533967"))
            .card(new YouthfulKnight())
            .build();

    public static final Blueprint BLUEPRINT_2 = Blueprint.builder()
            .id(UUID.fromString("c31a66c7-2f76-5f92-a922-835238533967"))
            .card(new VeteranKnight())
            .build();

    public static Set<Blueprint> getBlueprints() {

        return new HashSet<>(
                Arrays.asList(BLUEPRINT_1, BLUEPRINT_2)
        );
    }
}
