package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.model.player.Player;
import de.schramm.royalbash.persistence.GenericRepository;

public interface PlayerRepository extends GenericRepository<Player> {

    Player findByCredentials(
            String playername,
            String email,
            String passwordHash
    );

    Player findByName(String name);

    Player findByEmail(String email);
}
