package de.schramm.royalbash.controller.service

import de.schramm.royalbash.controller.service.core.Game
import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, String>
