package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.model.card.Weapon;
import de.schramm.royalbash.persistence.GenericRepository;

import java.util.Set;
import java.util.UUID;

public interface CardRepository extends GenericRepository<Card> {

    Set<UUID> findAllIds();

    Set<Card> findAll();

    void saveAll(Set<Card> cardSet);
}
