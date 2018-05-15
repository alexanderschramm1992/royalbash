package de.schramm.royalbash.data;

import de.schramm.royalbash.persistence.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Log4j2
@Configuration
public class DatabaseInitializer {

    private final GameRepository gameRepository;

    @Autowired
    public DatabaseInitializer(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostConstruct
    public void initializeDatabase() {

        gameRepository.save(GameData.GAME);
        log.info("Initialized database");
    }
}
