package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;

import java.util.*;

public class BoardData {

    public static Set<Board> getBoardSet() {

        return new HashSet<>(
                Collections.singletonList(
                        Board.builder()
                                .id(UUID.fromString("b030c982-ed9b-4fc4-89b0-377ecf5d228d"))
                                .turn(
                                        Turn.builder()
                                                .counter(1)
                                                .build()
                                )
                                .build()
                )
        );
    }
}
