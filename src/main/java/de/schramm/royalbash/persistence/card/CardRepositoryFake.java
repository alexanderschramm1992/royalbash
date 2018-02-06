package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.data.CardData;
import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.model.card.Weapon;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CardRepositoryFake implements CardRepository {

    private Map<UUID, CardEntity> cardEntityMap = new HashMap<>();

    @PostConstruct
    private void init() {

        saveAll(CardData.getCardSet());
    }

    @Override
    public Set<UUID> findAllIds() {

        return cardEntityMap.keySet();
    }

    @Override
    public Set<Card> findAll() {

        return cardEntityMap.keySet().stream()
                .map(this::find)
                .collect(Collectors.toSet());
    }

    @Override
    public Card find(UUID id) {

        CardEntity cardEntity = cardEntityMap.get(id);

        if(cardEntity != null) {

            if(cardEntity.getCardType() == InstanceType.Creature) {

                CreatureEntity creatureEntity = (CreatureEntity) cardEntity;

                return Creature.builder()
                        .id(creatureEntity.getId())
                        .name(creatureEntity.getName())
                        .cost(creatureEntity.getCost())
                        .build();
            } else if(cardEntity.getCardType() == InstanceType.Weapon) {

                WeaponEntity weaponEntity = (WeaponEntity) cardEntity;

                return Weapon.builder()
                        .id(weaponEntity.getId())
                        .name(weaponEntity.getName())
                        .cost(weaponEntity.getCost())
                        .build();
            } else {

                return null;
            }

        } else {

            return null;
        }
    }

    @Override
    public void saveAll(Set<Card> cardSet) {

        cardSet.forEach(this::save);
    }

    @Override
    public void save(Card card) {

        if(card.getInstanceType() == InstanceType.Creature) {

            cardEntityMap.put(card.getId(), CreatureEntity.toEntity((Creature) card));
        } else if(card.getInstanceType() == InstanceType.Weapon) {

            cardEntityMap.put(card.getId(), WeaponEntity.toEntity((Weapon) card));
        }
    }

    @Override
    public void delete(UUID id) {

        // Cards cannot be deleted
    }
}
