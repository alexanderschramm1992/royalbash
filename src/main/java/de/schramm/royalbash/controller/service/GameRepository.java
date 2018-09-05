package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {}
