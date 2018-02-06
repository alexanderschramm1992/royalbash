package de.schramm.royalbash.persistence;

import java.util.UUID;

public interface GenericRepository<T> {

    T find(UUID id);

    void save(T t);

    void delete(UUID id);
}
