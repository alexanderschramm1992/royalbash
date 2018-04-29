package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.model.card.Weapon;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WeaponRepositoryFake implements WeaponRepository {

    private Set<WeaponEntity> weaponEntitySet = new HashSet<>();

    @Override
    public Set<UUID> findAllIds() {

        return weaponEntitySet.stream()
                .map(WeaponEntity::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Weapon> findAll() {

        return weaponEntitySet.stream()
                .map(WeaponEntity::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void saveAll(Set<Weapon> weaponSet) {

        weaponSet.forEach(this::save);
    }

    @Override
    public Weapon find(UUID id) {

        return weaponEntitySet.stream()
                .filter(weaponEntity -> id.equals(weaponEntity.getId()))
                .map(WeaponEntity::fromEntity)
                .findFirst().orElse(null);
    }

    @Override
    public void save(Weapon weapon) {

        weaponEntitySet.add(WeaponEntity.toEntity(weapon));
    }

    @Override
    public void delete(UUID id) {

        Weapon weapon = find(id);

        if (weapon != null) {

            weaponEntitySet.remove(WeaponEntity.toEntity(weapon));
        }
    }
}
