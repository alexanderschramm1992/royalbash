package de.schramm.royalbash.persistence.board;

import de.schramm.royalbash.model.Board;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.deck.instance.DeckInstanceRepository;
import de.schramm.royalbash.persistence.player.instance.PlayerInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BoardRepositoryFake implements BoardRepository {

    private final CardInstanceRepository cardInstanceRepository;
    private final DeckInstanceRepository deckInstanceRepository;
    private final PlayerInstanceRepository playerInstanceRepository;

    private Map<UUID, BoardEntity> boardEntityMap = new HashMap<>();

    @Autowired
    public BoardRepositoryFake(
            CardInstanceRepository cardInstanceRepository,
            DeckInstanceRepository deckInstanceRepository,
            PlayerInstanceRepository playerInstanceRepository
    ) {
        this.cardInstanceRepository = cardInstanceRepository;
        this.deckInstanceRepository = deckInstanceRepository;
        this.playerInstanceRepository = playerInstanceRepository;
    }

    @Override
    public Board find(UUID id) {

        BoardEntity boardEntity = boardEntityMap.get(id);

        if (boardEntity != null) {

            return Board.builder()
                    .id(boardEntity.getId())
                    .turn(boardEntity.getTurn())
                    .playerBlueInstance(playerInstanceRepository.find(boardEntity.getPlayerBlueInstance()))
                    .playerRedInstance(playerInstanceRepository.find(boardEntity.getPlayerRedInstance()))
                    .playerBlueDeckInstance(deckInstanceRepository.find(boardEntity.getPlayerBlueDeckInstance()))
                    .playerRedDeckInstance(deckInstanceRepository.find(boardEntity.getPlayerRedDeckInstance()))
                    .blueInstanceSet(
                            boardEntity.getBlueInstanceSet().stream()
                                    .map(cardInstanceRepository::find)
                                    .collect(Collectors.toSet())
                    ).redInstanceSet(
                            boardEntity.getRedInstanceSet().stream()
                                    .map(cardInstanceRepository::find)
                                    .collect(Collectors.toSet())
                    ).build();
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

        cardInstanceRepository.delete(board.getPlayerBlueInstance().getId());

        cardInstanceRepository.delete(board.getPlayerRedInstance().getId());

        board.getBlueInstanceSet().forEach(instance -> cardInstanceRepository.delete(instance.getId()));

        board.getRedInstanceSet().forEach(instance -> cardInstanceRepository.delete(instance.getId()));
    }
}
