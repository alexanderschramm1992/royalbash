package de.schramm.royalbash.persistence.board;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.persistence.summoning.SummoningRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BoardRepositoryFake implements BoardRepository {

    private final SummoningRepository summoningRepository;
    private final PlayerRepository playerRepository;

    private Map<UUID, BoardEntity> boardEntityMap = new HashMap<>();

    @Autowired
    public BoardRepositoryFake(
            SummoningRepository summoningRepository,
            PlayerRepository playerRepository
    ) {
        this.summoningRepository = summoningRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Board find(UUID id) {

        BoardEntity boardEntity = boardEntityMap.get(id);

        if (boardEntity != null) {

            return Board.builder()
                    .id(boardEntity.getId())
                    .turn(boardEntity.getTurn())
                    .playerBlue(playerRepository.find(boardEntity.getPlayerBlue()))
                    .playerRed(playerRepository.find(boardEntity.getPlayerRed()))
                    .build();
        } else {

            return null;
        }
    }

    @Override
    public void save(Board board) {

        boardEntityMap.put(board.getId(), BoardEntity.toEntity(board));
    }

    @Override
    public void delete(UUID id) {

        Board board = find(id);
        boardEntityMap.remove(id);
        summoningRepository.delete(board.getPlayerBlue().getId());
        summoningRepository.delete(board.getPlayerRed().getId());
    }
}
