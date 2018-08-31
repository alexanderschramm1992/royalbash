package de.schramm.royalbash.tdd.core;

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

    public Game updatePlayer(Player oldPlayer, Player newPlayer) {
        return this.toBuilder()
                .player1(player1.equals(oldPlayer) ? newPlayer : player1)
                .player2(player2.equals(oldPlayer) ? newPlayer : player2)
                .build();
    }

    public Optional<Player> getPlayer(Player player) {
        return getPlayers()
                .filter(ownPlayer -> ownPlayer.equals(player))
                .findFirst();
    }
}
