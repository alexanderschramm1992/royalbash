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
    private String text;
    private String lore;
    private int strength;
    private int health;

    @Override
    public InstanceType getCardType() {

        return INSTANCE_TYPE;
    }

    public static CreatureEntity toEntity(Creature creature) {

        return CreatureEntity.builder()
                .id(creature.getId())
                .name(creature.getName())
                .cost(creature.getCost())
                .text(creature.getText())
                .lore(creature.getLore())
                .strength(creature.getStrength())
                .health(creature.getHealth())
                .build();
    }

    public static Creature fromEntity(CreatureEntity creatureEntity) {

        return Creature.builder()
                .id(creatureEntity.getId())
                .name(creatureEntity.getName())
                .cost(creatureEntity.getCost())
                .strength(creatureEntity.getStrength())
                .health(creatureEntity.getHealth())
                .build();
    }
}
