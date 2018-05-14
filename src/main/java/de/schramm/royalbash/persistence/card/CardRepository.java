package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.SummoningCard;
import de.schramm.royalbash.persistence.GenericRepository;

import java.util.Set;
import java.util.UUID;

public interface CardRepository extends GenericRepository<SummoningCard> {

    Set<UUID> findAllIds();

    Set<SummoningCard> findAll();

    void saveAll(Set<SummoningCard> summoningCardSet);
}
