package de.schramm.royalbash.persistence.board;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.model.Turn;
import de.schramm.royalbash.model.card.instance.CardInstance;
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
    private UUID playerBlueInstance;
    private UUID playerRedInstance;
    private UUID playerBlueDeckInstance;
    private UUID playerRedDeckInstance;
    @Singular("blueInstance")
    private Set<UUID> blueInstanceSet;
    @Singular("redInstance")
    private Set<UUID> redInstanceSet;

    public static BoardEntity toEntity(Board board) {

        return BoardEntity.builder()
                .id(board.getId())
                .turn(board.getTurn())
                .playerBlueInstance(board.getPlayerBlueInstance().getId())
                .playerRedInstance(board.getPlayerRedInstance().getId())
                .playerBlueDeckInstance(board.getPlayerBlueDeckInstance().getId())
                .playerRedDeckInstance(board.getPlayerRedDeckInstance().getId())
                .blueInstanceSet(board.getBlueInstanceSet().stream()
                        .map(CardInstance::getId)
                        .collect(Collectors.toSet())
                ).redInstanceSet(board.getRedInstanceSet().stream()
                        .map(CardInstance::getId)
                        .collect(Collectors.toSet())
                ).build();
    }
}
