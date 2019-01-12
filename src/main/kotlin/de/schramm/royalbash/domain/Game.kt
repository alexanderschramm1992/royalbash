package de.schramm.royalbash.domain

import java.util.*
import java.util.stream.Stream

data class Game(
        val id: String,
        val state: State = State.OPEN,
        val player1: Player,
        val player2: Player,
        val playerOnTurn: Player = player1
) {

    val players: Stream<Player>
        get() = Stream.of(player1, player2)

    fun playCard(card: Card, owner: Player, targetPlayer: Player): Game {
        return players
                .filter { player -> player == owner }
                .filter { player -> player.hasCard(card) }
                .findFirst()
                .map { player -> player.removeHandcard(card) }
                .map { player -> updatePlayer(owner, player) }
                .map { game -> card.invoke(Context(
                    game,
                    owner,
                    targetPlayer = targetPlayer)) }
                .orElse(this)
    }

    fun playCard(card: Card, owner: Player, targetSpot: Spot): Game {
        return players
                .filter { player -> player == owner }
                .filter { player -> player.hasCard(card) }
                .findFirst()
                .map { player -> player.removeHandcard(card) }
                .map { player -> updatePlayer(owner, player) }
                .map { game -> card.invoke(Context(
                        game,
                        owner,
                        targetSpot = targetSpot)) }
                .orElse(this)
    }

    fun combat(attacker: Creature, owner: Player, defender: Creature): Game {

        val opponent = getOpponent(owner)

        val attackerOptional = owner.getSpots()
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .filter { creature -> creature == attacker }
                .findFirst()

        val defenderOptional = opponent.getSpots()
                .map { it.getCreature() }
                .filter { it .isPresent }
                .map { it.get() }
                .filter { creature -> creature == defender }
                .findFirst()

        val updatedAttacker = attackerOptional
                .flatMap({ actualAttacker ->
                    defenderOptional.map { actualDefender -> actualAttacker.damage(actualDefender.attack) }
                })
                .orElse(attacker)

        val updatedDefender = defenderOptional
                .flatMap({ actualDefender ->
                    attackerOptional.map { actualAttacker -> actualDefender.damage(actualAttacker.attack) }
                })
                .orElse(defender)

        val updatedOwner = if (updatedAttacker.isDead())
            owner.removeCreature(attacker)
        else
            owner.updateCreature(attacker, updatedAttacker)

        val updatedOpponent = if (updatedDefender.isDead())
            opponent.removeCreature(defender)
        else
            opponent.updateCreature(defender, updatedDefender)

        return this
                .updatePlayer(owner, updatedOwner)
                .updatePlayer(opponent, updatedOpponent)
    }

    fun combat(attacker: Creature, owner: Player): Game {

        val opponent = getOpponent(owner)

        val attackerOptional = owner.getSpots()
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .filter { creature -> creature == attacker }
                .findFirst()

        val updatedOpponent = attackerOptional
                .map { actualAttacker -> opponent.setHitpoints(opponent.hitpoints - actualAttacker.attack) }
                .orElse(opponent)

        val player1 = if (player1 == opponent) updatedOpponent else player1
        val player2 = if (player2 == opponent) updatedOpponent else player2

        return copy(
                player1 = player1,
                player2 = player2,
                state = evaluateState(player1, player2))
    }

    fun updatePlayer(oldPlayer: Player, newPlayer: Player): Game {
        return copy(
                player1 = if (player1 == oldPlayer) newPlayer else player1,
                player2 = if (player2 == oldPlayer) newPlayer else player2)
    }

    fun updateCreature(oldCreature: Creature, newCreature: Creature): Game {
        return copy(
                player1 = player1.updateCreature(oldCreature, newCreature),
                player2 = player2.updateCreature(oldCreature, newCreature))
    }

    fun findPlayer(player: Player): Optional<Player> {
        return players
                .filter { ownPlayer -> ownPlayer == player }
                .findFirst()
    }

    fun findPlayer(playerId: String): Optional<Player> {
        return Stream.of<Player>(player1, player2)
                .filter { it.id == playerId }
                .findFirst()
    }

    fun findCreature(creatureId: String): Optional<Creature> {
        return Stream.of<Player>(player1, player2)
                .flatMap { it.getSpots() }
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .filter { it.id == creatureId }
                .findFirst()
    }

    fun findCreature(creature: Creature): Optional<Creature> {
        return Stream.of<Player>(player1, player2)
                .flatMap { it.getSpots() }
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .filter { it == creature }
                .findFirst()
    }

    fun findSpot(spotId: String): Optional<Spot> {
        return Stream.of<Player>(player1, player2)
                .flatMap { it.getSpots() }
                .filter { it.id == spotId }
                .findFirst()
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
            else -> state
        }
    }

    private fun getOpponent(player: Player): Player {
        return if (player1 == player) player2 else player1
    }

    fun removeCreature(creature: Creature): Game {
        return copy(
                player1 = player1.removeCreature(creature),
                player2 = player2.removeCreature(creature))
    }
}
