package de.schramm.royalbash.domain

import java.util.*

data class Game(val id: String, val state: State = State.OPEN, val player1: Player, val player2: Player,
                val playerOnTurn: Player = player1) {

    val players: List<Player>
        get() = listOf(player1, player2)

    fun playCard(card: Card, owner: Player, targetPlayer: Player): Game {
        return players.filter { it == owner }.firstOrNull { it.hasCard(card) }?.removeHandcard(
                        card)?.let { updatePlayer(owner, it) }?.let {
                    card.invoke(Context(it, owner, targetPlayer = targetPlayer))
                } ?: this
    }

    fun playCard(card: Card, owner: Player, targetSpot: Spot): Game {
        return players.filter { player -> player == owner }.firstOrNull { player ->
                    player.hasCard(card)
                }?.removeHandcard(card)?.let { updatePlayer(owner, it) }?.let {
                    card.invoke(Context(it, owner, targetSpot = targetSpot))
                } ?: this
    }

    fun combat(attacker: Creature, owner: Player, defender: Creature): Game {

        val opponent = getOpponent(owner)

        val attackerOptional =
                Optional.ofNullable(owner.spots.mapNotNull { it.creature }.firstOrNull { it == attacker })

        val defenderOptional =
                Optional.ofNullable(opponent.spots.mapNotNull { it.creature }.firstOrNull { it == defender })

        val updatedAttacker =
                attackerOptional.flatMap { actualAttacker -> defenderOptional.map { actualAttacker.damage(it.attack) } }
                        .orElse(attacker)

        val updatedDefender =
                defenderOptional.flatMap { actualDefender -> attackerOptional.map { actualDefender.damage(it.attack) } }
                        .orElse(defender)

        val updatedOwner = if (updatedAttacker.isDead()) owner.removeCreature(attacker)
        else owner.updateCreature(attacker, updatedAttacker)

        val updatedOpponent = if (updatedDefender.isDead()) opponent.removeCreature(defender)
        else opponent.updateCreature(defender, updatedDefender)

        return this.updatePlayer(owner, updatedOwner)
                .updatePlayer(opponent, updatedOpponent)
    }

    fun combat(attacker: Creature, owner: Player): Game {

        val opponent = getOpponent(owner)

        val attackerOptional =
                Optional.ofNullable(owner.spots.mapNotNull { it.creature }.firstOrNull { it == attacker })

        val updatedOpponent = attackerOptional.map { opponent.setHitpoints(opponent.hitpoints - it.attack) }
                .orElse(opponent)

        val player1 = if (player1 == opponent) updatedOpponent else player1
        val player2 = if (player2 == opponent) updatedOpponent else player2

        return copy(player1 = player1, player2 = player2, state = evaluateState(player1, player2))
    }

    fun updatePlayer(oldPlayer: Player, newPlayer: Player): Game {
        return copy(player1 = if (player1 == oldPlayer) newPlayer else player1,
                    player2 = if (player2 == oldPlayer) newPlayer else player2)
    }

    fun updateCreature(oldCreature: Creature, newCreature: Creature) = copy(
            player1 = player1.updateCreature(oldCreature, newCreature),
            player2 = player2.updateCreature(oldCreature, newCreature))

    fun findPlayer(player: Player) = players.firstOrNull { player == it }

    fun findPlayer(playerId: String) = listOf(player1, player2).firstOrNull { it.id == playerId }

    fun findCreature(creatureId: String) = listOf(player1, player2)
                .flatMap { it.spots }
                .mapNotNull { it.creature }
                .firstOrNull { it.id == creatureId }

    fun findCreature(creature: Creature) = listOf(player1, player2)
            .flatMap { it.spots }
            .mapNotNull { it.creature }
            .firstOrNull { it == creature }

    fun findSpot(spotId: String): Spot? {
        return listOf(player1.spots, player2.spots).flatten()
                .find { spot -> spot.id == spotId }
    }

    internal fun nextTurn(): Game {
        return copy(playerOnTurn = if (playerOnTurn == player1) player2 else player1)
    }

    fun setState(state: State): Game {
        return copy(state = state)
    }

    internal fun updateStateAccordingToWinContitions(): Game {

        val hitpointsOfPlayer1 = player1.hitpoints
        val hitpointsOfPlayer2 = player2.hitpoints

        var state = if (hitpointsOfPlayer1 <= 0) State.PLAYER_2_WON else this.state
        state = if (hitpointsOfPlayer2 <= 0) State.PLAYER_1_WON else state

        return setState(state)
    }

    private fun evaluateState(player1: Player, player2: Player): State {
        return when {
            player1.hitpoints <= 0 -> State.PLAYER_2_WON
            player2.hitpoints <= 0 -> State.PLAYER_1_WON
            else                   -> state
        }
    }

    private fun getOpponent(player: Player): Player {
        return if (player1 == player) player2 else player1
    }

    fun removeCreature(creature: Creature): Game {
        return copy(player1 = player1.removeCreature(creature), player2 = player2.removeCreature(creature))
    }
}
