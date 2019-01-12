package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game
import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, String>
