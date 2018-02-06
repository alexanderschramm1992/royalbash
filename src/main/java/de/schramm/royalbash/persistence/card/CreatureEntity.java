package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.Creature;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatureEntity implements CardEntity {

    private static final InstanceType INSTANCE_TYPE = InstanceType.Creature;

    private UUID id;
    private String name;
    private int cost;

    @Override
    public InstanceType getCardType() {

        return INSTANCE_TYPE;
    }

    public static CreatureEntity toEntity(Creature creature) {

        return CreatureEntity.builder()
                .id(creature.getId())
                .name(creature.getName())
                .cost(creature.getCost())
                .build();
    }
}
