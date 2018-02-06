package de.schramm.royalbash.persistence.card.instance;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.WeaponInstance;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class WeaponInstanceEntity implements CardInstanceEntity {

    private final InstanceType instanceType = InstanceType.Weapon;

    private UUID id;
    private String name;

    private int cost;
    private int currentHealth;
    private int currentStrength;

    @Override
    public InstanceType getInstanceType() {

        return instanceType;
    }

    public static WeaponInstanceEntity toEntity(WeaponInstance weaponInstance) {

        return WeaponInstanceEntity.builder()
                .id(weaponInstance.getId())
                .name(weaponInstance.getName())
                .currentHealth(weaponInstance.getCurrentHealth())
                .currentStrength(weaponInstance.getCurrentStrength())
                .build();
    }
}
