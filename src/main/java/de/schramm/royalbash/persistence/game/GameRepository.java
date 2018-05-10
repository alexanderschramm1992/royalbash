package de.schramm.royalbash.persistence.game;

import de.schramm.royalbash.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
}
