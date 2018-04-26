package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Summoning;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SummoningOwnedByPlayerOnBoardChecker {

    public void check(
            Board board,
            Summoning... summonings
    ) throws GameRuleViolationException {

        Set<Summoning> summoningsOnBoard = Stream.of(
                board.getPlayerBlue().getSummonings(),
                board.getPlayerRed().getSummonings()
        )
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Set<UUID> summoningsNotOnBoard = Arrays.stream(summonings)
                .filter(summoning -> !summoningsOnBoard.contains(summoning))
                .map(Summoning::getId)
                .collect(Collectors.toSet());

        if (!summoningsNotOnBoard.isEmpty()) {

            throw new GameRuleViolationException(
                    String.format(
                            "Summonings %s are not or are not owned by Players on Board %s",
                            summoningsNotOnBoard,
                            board.getId()
                    )
            );
        }
    }
}
