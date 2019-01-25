package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player

internal fun Player.updateInGame(game: Game, oldPlayer: Player): Game = game.updatePlayer(oldPlayer, this)
