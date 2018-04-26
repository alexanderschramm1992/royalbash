package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.persistence.GenericRepository;

import java.util.Set;
import java.util.UUID;

public interface CardRepository extends GenericRepository<Card> {

    Set<UUID> findAllIds();

    Set<Card> findAll();

    void saveAll(Set<Card> cardSet);
}
