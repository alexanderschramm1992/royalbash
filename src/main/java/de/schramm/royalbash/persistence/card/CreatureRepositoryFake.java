package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.card.Creature;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CreatureRepositoryFake implements CreatureRepository {

    private Set<CreatureEntity> creatureEntitySet = new HashSet<>();

    @Override
    public Set<UUID> findAllIds() {

        return creatureEntitySet.stream()
                .map(CreatureEntity::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Creature> findAll() {

        return creatureEntitySet.stream()
                .map(CreatureEntity::getId)
                .map(this::find)
                .collect(Collectors.toSet());
    }

    @Override
    public void saveAll(Set<Creature> creatureSet) {

        creatureSet.forEach(this::save);
    }

    @Override
    public Creature find(UUID id) {

        return creatureEntitySet.stream()
                .filter(creatureEntity -> id.equals(creatureEntity.getId()))
                .map(CreatureEntity::fromEntity)
                .findFirst().orElse(null);
    }

    @Override
    public void save(Creature creature) {

        creatureEntitySet.add(CreatureEntity.toEntity(creature));
    }

    @Override
    public void delete(UUID id) {

        Creature creature = find(id);

        if (creature != null) {

            creatureEntitySet.remove(CreatureEntity.toEntity(creature));
        }
    }
}
