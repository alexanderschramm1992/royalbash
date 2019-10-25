package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.UUIDGenerator
import de.schramm.royalbash.domain.log

fun Game.logPlayerMissing(uuidGenerator: UUIDGenerator) =
        log(uuidGenerator, "Missing player")

fun Game.logNotTurnOfPlayer(uuidGenerator: UUIDGenerator, player: Player, playerOnTurn: Player) =
        log(uuidGenerator, "Player ${player.name} not player on turn, which is player ${playerOnTurn.name}")

fun Game.logTurnEnded(uuidGenerator: UUIDGenerator, lastPlayerOnTurn: Player, newPlayerOnTurn: Player?) =
        log(uuidGenerator, "${lastPlayerOnTurn.name} ended the turn, it is now ${newPlayerOnTurn?.name}'s turn")
