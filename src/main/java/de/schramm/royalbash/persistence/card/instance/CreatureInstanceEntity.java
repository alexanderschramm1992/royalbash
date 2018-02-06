package de.schramm.royalbash.persistence.card.instance;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.card.instance.CreatureInstance;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class CreatureInstanceEntity implements CardInstanceEntity {

    private static final InstanceType INSTANCE_TYPE = InstanceType.Creature;

    private UUID id;
    private String name;

    private int cost;
    private int currentHealth;
    private int currentStrength;

    @Singular("equippable")
    private Set<UUID> equippedSet;

    @Override
    public InstanceType getInstanceType() {

        return INSTANCE_TYPE;
    }

    public static CreatureInstanceEntity toEntity(CreatureInstance creatureInstance) {

        return CreatureInstanceEntity.builder()
                .id(creatureInstance.getId())
                .name(creatureInstance.getName())
                .currentHealth(creatureInstance.getCurrentHealth())
                .currentStrength(creatureInstance.getCurrentStrength())
                .equippedSet(
                        creatureInstance.getEquippedSet().stream()
                                .map(CardInstance::getId)
                                .collect(Collectors.toSet())
                ).build();
    }
}
