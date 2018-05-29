package de.schramm.royalbash.persistence;

import de.schramm.royalbash.gameengine.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<Game, UUID> {

    Game findGameById(UUID id);
}
