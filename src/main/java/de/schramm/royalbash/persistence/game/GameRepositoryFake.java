package de.schramm.royalbash.persistence.game;

import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.persistence.board.BoardRepository;
import de.schramm.royalbash.persistence.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GameRepositoryFake implements GameRepository {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Set<GameEntity> gameSet = new HashSet<>();

    @Override
    public Game find(UUID id) {

        Optional<GameEntity> gameOptional = gameSet.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();

        if (gameOptional.isPresent()) {

            GameEntity gameEntity = gameOptional.get();

            return Game.builder()
                    .id(gameEntity.getId())
                    .accountBlue(accountRepository.find(gameEntity.getAccountBlue()))
                    .accountRed(accountRepository.find(gameEntity.getAccountRed()))
                    .board(boardRepository.find(gameEntity.getBoard()))
                    .build();
        } else {

            return null;
        }
    }

    @Override
    public void save(Game game) {

        boardRepository.save(game.getBoard());

        gameSet.add(GameEntity.toEntity(game));
    }

    @Override
    public void delete(UUID id) {

        Game game = find(id);

        gameSet.remove(GameEntity.toEntity(game));

        boardRepository.delete(game.getBoard().getId());
    }
}
