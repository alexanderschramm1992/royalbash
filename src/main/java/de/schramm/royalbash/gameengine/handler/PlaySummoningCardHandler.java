package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.model.Target;
import de.schramm.royalbash.model.summoningcard.SummoningCard;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlaySummoningCardHandler {

    public Game summon(
            Game game,
            Player player,
            SummoningCard summoningCard,
            Target target
    ) throws GameEngineException {

        player.getHand().removeCard(summoningCard);
        Summoning summoning = Summoning.fromCard(summoningCard, UUID.randomUUID());
        player.summon(summoning, target);

        return game;
    }
}
