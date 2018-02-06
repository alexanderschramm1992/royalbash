package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.model.card.Weapon;

import java.util.UUID;

public interface CardEntity {

    UUID getId();
    InstanceType getCardType();
    String getName();

    int getCost();

    static CardEntity toEntity(Card card) {

        if(card.getInstanceType() == InstanceType.Creature) {

            return CreatureEntity.toEntity((Creature) card);
        } else if(card.getInstanceType() == InstanceType.Weapon) {

            return WeaponEntity.toEntity((Weapon) card);
        } else {

            return null;
        }
    }

}
