package de.schramm.royalbash.data;

import de.schramm.royalbash.gameengine.model.Field;
import de.schramm.royalbash.gameengine.model.Target;

import java.util.UUID;

class FieldData {

    static final Field FIELD_1 = Field.builder()
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

    static final Field FIELD_2 = Field.builder()
            .target(
                    Target.builder()
                            .id(UUID.fromString("bbfc3e08-db9f-49ba-8028-b24c61fe0a1a"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("f9f7311d-f348-4dd7-a030-a71af733a9f0"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("2c36d72e-fa79-424a-875d-4e064894e6f1"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("d073e4d6-b295-45bb-8094-2d4e6593685c"))
                            .build()
            )
            .target(
                    Target.builder()
                            .id(UUID.fromString("d073e4d6-b295-45bb-8094-2d4e6593685c"))
                            .build()
            )
            .build();
}
