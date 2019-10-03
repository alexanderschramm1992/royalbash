package de.schramm.royalbash.domain

fun Game.log(log: Log): Game = copy(logs = logs + log)

fun Game.log(id: String, message: String): Game = log(Log(id, message))

fun Game.playCard(card: Card, owner: Player, targetPlayer: Player): Game =
        players[owner]
                ?.takeIf { it.hasCard(card) }
                ?.removeHandcard(card)
                ?.let { updatePlayer(owner to it) }
                ?.let { card.invoke(Context(it, owner, targetPlayer = targetPlayer)) }
        ?: this

fun Game.playCard(card: Card, owner: Player, targetSpot: Spot): Game =
        players[owner]
                ?.takeIf { it.hasCard(card) }
                ?.removeHandcard(card)
                ?.let { updatePlayer(owner to it) }
                ?.let { card.invoke(Context(it, owner, targetSpot = targetSpot)) }
        ?: this

fun Game.combat(attacker: Creature, owner: Player, defender: Creature): Game {

    val opponent = opponentOf(owner)

    val actualAttacker = owner.spots.mapNotNull(Spot::creature)
            .firstOrNull { it == attacker }

    val axctualDefender = opponent.spots.mapNotNull(Spot::creature)
            .firstOrNull { it == defender }

    val updatedAttacker = if (actualAttacker != null && axctualDefender != null)
        actualAttacker.damage(axctualDefender.attack)
    else attacker

    val updatedDefender = if (actualAttacker != null && axctualDefender != null)
        axctualDefender.damage(actualAttacker.attack)
    else defender

    val updatedOwner = if (updatedAttacker.isDead()) owner.removeCreature(attacker)
    else owner.updateCreature(attacker to updatedAttacker)

    val updatedOpponent = if (updatedDefender.isDead()) opponent.removeCreature(defender)
    else opponent.updateCreature(defender to updatedDefender)

    return this.updatePlayer(owner to updatedOwner)
            .updatePlayer(opponent to updatedOpponent)
}

fun Game.combat(attacker: Creature, owner: Player): Game {

    val opponent = opponentOf(owner)

    val actualAttacker = owner.spots.mapNotNull(Spot::creature)
            .firstOrNull { it == attacker }

    val updatedOpponent = actualAttacker
                                  ?.attack
                                  ?.let { opponent.withHitpoints(opponent.hitpoints - it) }
                          ?: opponent

    val player1 = if (player1 == opponent) updatedOpponent else player1
    val player2 = if (player2 == opponent) updatedOpponent else player2

    return copy(player1 = player1, player2 = player2, state = evaluateState(player1, player2))
}

fun Game.updatePlayer(oldToNew: Pair<Player, Player>): Game = copy(
        player1 = if (player1 == oldToNew.old) oldToNew.new else player1,
        player2 = if (player2 == oldToNew.old) oldToNew.new else player2)

fun Game.updateCreature(oldToNew: Pair<Creature, Creature>): Game = copy(
        player1 = player1.updateCreature(oldToNew),
        player2 = player2.updateCreature(oldToNew))

fun Game.findPlayer(player: Player) = players.firstOrNull { player == it }

fun Game.findPlayer(playerId: String) = players.firstOrNull { it.id == playerId }

fun Game.findCreature(creatureId: String) = players
        .flatMap { it.spots }
        .mapNotNull { it.creature }
        .firstOrNull { it.id == creatureId }

fun Game.findCreature(creature: Creature) = players
        .flatMap { it.spots }
        .mapNotNull { it.creature }
        .firstOrNull { it == creature }

fun Game.findSpot(spotId: String): Spot? = players
        .flatMap { it.spots }
        .find { it.id == spotId }

fun Game.opponentOf(player: Player): Player = if (player1 == player) player2 else player1

fun Game.nextTurn(): Game = copy(playerOnTurn = if (playerOnTurn == player1) player2 else player1)

fun Game.withState(state: State): Game = copy(state = state)

fun Game.updateStateAccordingToWinConditions(): Game = withState(evaluateState(player1, player2))

fun Game.removeCreature(creature: Creature): Game =
        copy(player1 = player1.removeCreature(creature), player2 = player2.removeCreature(creature))

val Game.players: List<Player>
    get() = listOf(player1, player2)

private fun Game.evaluateState(player1: Player, player2: Player): State = when {
    player1.hitpoints <= 0 -> State.PLAYER_2_WON
    player2.hitpoints <= 0 -> State.PLAYER_1_WON
    else                   -> state
}

private operator fun List<Player>.get(player: Player) = this.firstOrNull { it == player }
fun Game.switchPlayerOnTurn() = copy(playerOnTurn = if (playerOnTurn == player1) player2 else player1)
