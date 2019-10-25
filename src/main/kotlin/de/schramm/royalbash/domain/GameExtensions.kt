package de.schramm.royalbash.domain

fun Game.log(log: Log): Game = copy(logs = logs + log)

fun Game.log(id: String, message: String): Game = log(Log(id, message))

fun Game.log(uuidGenerator: UUIDGenerator, message: String): Game = log(uuidGenerator.id(), message)

fun Game.updatePlayer(oldPlayerId: String, transition: (player: Player) -> Player): Game =
        findPlayer(oldPlayerId)?.let { updatePlayer(it, transition) } ?: this

fun Game.updatePlayer(oldPlayer: Player, transition: (player: Player) -> Player): Game =
        findPlayer(oldPlayer)?.let { updatePlayer(it to transition(it)) } ?: this

fun Game.updatePlayer(oldToNew: Pair<Player?, Player>): Game = copy(
        player1 = if (player1.id == oldToNew.old?.id) oldToNew.new else player1,
        player2 = if (player2.id == oldToNew.old?.id) oldToNew.new else player2)

fun Game.updateCreature(oldToNew: Pair<Creature, Creature>): Game = copy(
        player1 = player1.updateCreature(oldToNew),
        player2 = player2.updateCreature(oldToNew))

fun Game.findPlayer(player: Player) = players.firstOrNull { player == it }

fun Game.findPlayer(playerId: String?) = players.firstOrNull { it.id == playerId }

fun Game.findHandcard(instanceId: String?) = players.flatMap { it.handcards }
        .find { it.instanceId == instanceId }

fun Game.findCreature(instanceId: String?) = players
        .flatMap { it.spots }
        .mapNotNull { it.creature }
        .firstOrNull { it.instanceId == instanceId }

fun Game.findCreature(creature: Creature) = players
        .flatMap { it.spots }
        .mapNotNull { it.creature }
        .firstOrNull { it == creature }

fun Game.findSpot(spotId: String?): Spot? = players
        .flatMap { it.spots }
        .find { it.id == spotId }

fun Game.opponentOf(player: Player?): Player? = when (player) {
    player1 -> player2
    player2 -> player1
    else    -> null
}

fun Game.nextTurn(): Game = copy(playerOnTurn = if (playerOnTurn == player1) player2 else player1)

fun Game.withState(state: State): Game = copy(state = state)

fun Game.updateStateAccordingToWinConditions(): Game = withState(evaluateState(player1, player2))

fun Game.buryCreature(creature: Creature): Game =
        copy(player1 = player1.buryCreature(creature), player2 = player2.buryCreature(creature))

fun Game.ownerOf(creature: Creature?): Player? = when (creature) {
    in player1.spots.mapNotNull(Spot::creature) -> player1
    in player2.spots.mapNotNull(Spot::creature) -> player2
    else                                        -> null
}

fun Game.reduceResourcesBy(amount: Int, player: Player): Game = when {
    player1 == player -> copy(player1 = player1.reduceResourcesBy(amount))
    player2 == player -> copy(player2 = player2.reduceResourcesBy(amount))
    else              -> this
}

fun Game.buryDeadCreatures(uuidGenerator: UUIDGenerator): Game = creatures
        .filter { it.hitpoints <= 0 }
        .fold(this) { game, creature ->
            game.buryCreature(creature).logCreatureRemoved(uuidGenerator, creature)
        }

val Game.players: List<Player>
    get() = listOf(player1, player2)

val Game.creatures: List<Creature>
    get() = players.flatMap(Player::spots).mapNotNull(Spot::creature)

private fun Game.evaluateState(player1: Player, player2: Player): State = when {
    player1.hitpoints <= 0 -> State.PLAYER_2_WON
    player2.hitpoints <= 0 -> State.PLAYER_1_WON
    else                   -> state
}

private operator fun List<Player>.get(player: Player) = this.firstOrNull { it == player }
fun Game.switchPlayerOnTurn() = copy(playerOnTurn = if (playerOnTurn == player1) player2 else player1)
