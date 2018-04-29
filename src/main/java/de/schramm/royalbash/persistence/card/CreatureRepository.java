package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.persistence.GenericRepository;

import java.util.Set;
import java.util.UUID;

public interface CreatureRepository extends GenericRepository<Creature> {

    Set<UUID> findAllIds();

    Set<Creature> findAll();

    void saveAll(Set<Creature> creatureSet);
}
