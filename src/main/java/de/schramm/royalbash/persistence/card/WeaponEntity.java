package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.Weapon;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class WeaponEntity implements CardEntity{

    private static final InstanceType INSTANCE_TYPE = InstanceType.Creature;

    private UUID id;
    private String name;
    private int cost;

    @Override
    public InstanceType getCardType() {

        return INSTANCE_TYPE;
    }

    public static WeaponEntity toEntity(Weapon weapon) {

        return WeaponEntity.builder()
                .id(weapon.getId())
                .name(weapon.getName())
                .cost(weapon.getCost())
                .build();
    }
}
