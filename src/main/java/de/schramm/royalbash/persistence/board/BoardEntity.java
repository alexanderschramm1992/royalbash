package de.schramm.royalbash.persistence.board;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class BoardEntity {

    private UUID id;
    private Turn turn;
    private UUID playerBlue;
    private UUID playerRed;

    public static BoardEntity toEntity(Board board) {

        return BoardEntity.builder()
                .id(board.getId())
                .turn(board.getTurn())
                .playerBlue(board.getPlayerBlue().getId())
                .playerRed(board.getPlayerRed().getId())
                .build();
    }
}
