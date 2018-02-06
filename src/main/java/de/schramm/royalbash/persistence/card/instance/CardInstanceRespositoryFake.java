package de.schramm.royalbash.persistence.card.instance;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.card.instance.CreatureInstance;
import de.schramm.royalbash.model.card.instance.Equippable;
import de.schramm.royalbash.model.card.instance.WeaponInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CardInstanceRespositoryFake implements CardInstanceRepository {

    private Map<UUID, CardInstanceEntity> cardInstanceEntityMap = new HashMap<>();

    @Override
    public CardInstance find(UUID id) {

        CardInstanceEntity cardInstanceEntity = cardInstanceEntityMap.get(id);

        if(cardInstanceEntity != null) {

            if(cardInstanceEntity.getInstanceType() == InstanceType.Creature) {

                CreatureInstanceEntity creatureInstanceEntity = (CreatureInstanceEntity) cardInstanceEntity;

                return CreatureInstance.builder()
                        .id(creatureInstanceEntity.getId())
                        .name(creatureInstanceEntity.getName())
                        .cost(creatureInstanceEntity.getCost())
                        .currentHealth(creatureInstanceEntity.getCurrentHealth())
                        .currentStrength(creatureInstanceEntity.getCurrentStrength())
                        .equippedSet(creatureInstanceEntity.getEquippedSet().stream()
                                .map(equippableId -> (Equippable) find(equippableId))
                                .collect(Collectors.toSet())
                        ).build();

            } else if(cardInstanceEntity.getInstanceType() == InstanceType.Weapon) {

                WeaponInstanceEntity weaponInstanceEntity = (WeaponInstanceEntity) cardInstanceEntity;

                return WeaponInstance.builder()
                        .id(weaponInstanceEntity.getId())
                        .name(weaponInstanceEntity.getName())
                        .cost(weaponInstanceEntity.getCost())
                        .currentHealth(weaponInstanceEntity.getCurrentHealth())
                        .currentStrength(weaponInstanceEntity.getCurrentStrength())
                        .build();

            } else {

                return null;
            }

        } else {

            return null;
        }
    }

    @Override
    public void save(CardInstance cardInstance) {

        cardInstanceEntityMap.put(cardInstance.getId(), CardInstanceEntity.toEntity(cardInstance));
    }

    @Override
    public void delete(UUID id) {

        cardInstanceEntityMap.remove(id);
    }
}
