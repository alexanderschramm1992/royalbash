package de.schramm.royalbash.persistence.target;

import de.schramm.royalbash.model.Target;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TargetEntity {

    private UUID id;
    private UUID summoning;

    public static TargetEntity toEntity(Target target) {

        return TargetEntity.builder()
                .id(target.getId())
                .summoning(target.getSummoning().getId())
                .build();
    }
}
