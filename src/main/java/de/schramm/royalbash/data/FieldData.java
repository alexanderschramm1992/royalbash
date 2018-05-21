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
            .target(
                    Target.builder()
                            .id(UUID.fromString("30a2eaed-1421-4067-b51a-fe5754290965"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("6e8df99e-566b-4a0f-b00e-3a5492643025"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("2563eec4-e60d-449c-a3b9-fd90e648330c"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("72f63620-d5ef-494f-83ce-3066c8b174ea"))
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
