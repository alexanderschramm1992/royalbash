package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game

interface GameEvent {
    fun invoke(game: Game): Game
}