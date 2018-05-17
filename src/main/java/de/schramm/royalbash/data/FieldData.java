package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Field;
import de.schramm.royalbash.model.Target;

import java.util.UUID;

public class FieldData {

    public static final Field FIELD_1 = Field.builder()
            .target(
                    Target.builder()
                            .id(UUID.fromString("fe407ffc-625f-4d8f-af48-6f381d1b3a1b"))
                            .build()
            )
            .build();

    public static final Field FIELD_2 = Field.builder()
            .target(
                    Target.builder()
                            .id(UUID.fromString("bbfc3e08-db9f-49ba-8028-b24c61fe0a1a"))
                            .build()
            )
            .build();
}
