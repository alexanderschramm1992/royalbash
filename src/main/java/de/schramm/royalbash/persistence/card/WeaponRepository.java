package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.card.Weapon;
import de.schramm.royalbash.persistence.GenericRepository;

import java.util.Set;
import java.util.UUID;

public interface WeaponRepository extends GenericRepository<Weapon> {

    Set<UUID> findAllIds();

    Set<Weapon> findAll();

    void saveAll(Set<Weapon> weaponSet);
}
