package de.schramm.royalbash.persistence.card.instance;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.card.instance.CreatureInstance;
import de.schramm.royalbash.model.card.instance.WeaponInstance;

import java.util.UUID;

public interface CardInstanceEntity {

    UUID getId();
    InstanceType getInstanceType();
    String getName();

    int getCost();

    static CardInstanceEntity toEntity(CardInstance cardInstance) {

        if(cardInstance.getInstanceType() == InstanceType.Creature) {

            return CreatureInstanceEntity.toEntity((CreatureInstance) cardInstance);
        } else if(cardInstance.getInstanceType() == InstanceType.Weapon) {

            return WeaponInstanceEntity.toEntity((WeaponInstance) cardInstance);
        } else {

            return null;
        }
    }
}
