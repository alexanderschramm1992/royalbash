package de.schramm.royalbash.controller.service.core;

import lombok.Builder;
import lombok.Value;
import lombok.val;

import java.util.Optional;
import java.util.stream.Stream;

@Value
@Builder(toBuilder = true)
public class Game {

    private final State state;
    private final Player player1;
    private final Player player2;
    private final Player playerOnTurn;

    Stream<Player> getPlayers() {
        return Stream.of(player1, player2);
    }

    Game nextTurn() {
        return this.toBuilder()
                .playerOnTurn(playerOnTurn.equals(player1) ? player2 : player1)
                .build();
    }

    Game setState(State state) {
        return this.toBuilder()
                .state(state)
                .build();
    }

    Game updateStateAccordingToWinContitions() {

        val hitpointsOfPlayer1 = player1.getHitpoints();
        val hitpointsOfPlayer2 = player2.getHitpoints();

        State state = hitpointsOfPlayer1 <= 0 ? State.PLAYER_2_WON : this.state;
        state = hitpointsOfPlayer2 <= 0 ? State.PLAYER_1_WON : state;

        return this.toBuilder()
                .state(state)
                .build();
    }

    Game playCard(Card card, Player owner, Player target, Spot targetSpot) {
        return getPlayers()
                .filter(player -> player.equals(owner))
                .filter(player -> player.hasCard(card))
                .findFirst()
                .map(player -> player.removeHandcard(card))
                .map(player -> updatePlayer(owner, player))
                .map(game -> card.invoke(
                        Context.builder()
                            .game(game)
                            .owner(owner)
                            .targetPlayer(target)
                            .targetSpot(targetSpot)
                            .build()
                ))
                .orElse(this);
    }

    Game combat(Creature attacker, Player owner, Creature defender) {

        val opponent = getOpponent(owner);

        val attackerOptional = owner.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(creature -> creature.equals(attacker))
                .findFirst();

        val defenderOptional = opponent.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(creature -> creature.equals(defender))
                .findFirst();

        val updatedAttacker = attackerOptional
                .flatMap(actualAttacker -> defenderOptional
                        .map(actualAttacker::damage)
                )
                .orElse(attacker);

        val updatedDefender =defenderOptional
                .flatMap(actualDefender -> attackerOptional
                        .map(actualDefender::damage)
                ).orElse(defender);

        val updatedOwner = updatedAttacker.isDead() ? owner.removeCreature(attacker) : owner.updateCreature(
                attacker,
                updatedAttacker
        );

        val updatedOpponent = updatedDefender.isDead() ? opponent.removeCreature(defender) : opponent.updateCreature(
                defender,
                updatedDefender
        );

        return this
                .updatePlayer(owner, updatedOwner)
                .updatePlayer(opponent, updatedOpponent);
    }

    public Game combat(Creature attacker, Player owner) {

        val opponent = getOpponent(owner);

        val attackerOptional = owner.getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(creature -> creature.equals(attacker))
                .findFirst();

        val updatedOpponent = attackerOptional
                .map(actualAttakecker -> opponent.setHitpoints(opponent.getHitpoints() - actualAttakecker.getAttack()))
                .orElse(opponent);

        val game = this.toBuilder()
                .player1(player1.equals(opponent) ? updatedOpponent : player1)
                .player2(player2.equals(opponent) ? updatedOpponent : player2)
                .build();

        return game.toBuilder()
                .state(evaluateState(game.player1, game.player2))
                .build();
    }

    private State evaluateState(Player player1, Player player2) {
        if(player1.getHitpoints() <= 0) {
            return State.PLAYER_2_WON;
        } else if(player2.getHitpoints() <= 0) {
            return State.PLAYER_1_WON;
        } else {
            return state;
        }
    }

    private Player getOpponent(Player player) {
        return player1.equals(player) ? player2 : player1;
    }

    public Game updatePlayer(Player oldPlayer, Player newPlayer) {
        return this.toBuilder()
                .player1(player1.equals(oldPlayer) ? newPlayer : player1)
                .player2(player2.equals(oldPlayer) ? newPlayer : player2)
                .build();
    }

    public Optional<Player> findPlayer(Player player) {
        return getPlayers()
                .filter(ownPlayer -> ownPlayer.equals(player))
                .findFirst();
    }

    public Optional<Player> findPlayer(String playerId) {
        return Stream.of(player1, player2)
                .filter(player -> playerId.equals(player.getId()))
                .findFirst();
    }

    public Optional<Creature> findCreature(String creatureId) {
        return Stream.of(player1, player2)
                .flatMap(Player::getSpots)
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(creature -> creatureId.equals(creature.getId()))
                .findFirst();
    }
}
