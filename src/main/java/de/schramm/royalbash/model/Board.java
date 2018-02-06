package de.schramm.royalbash.model;

import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.deck.DeckInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
public class Board {

    private final UUID id;
    private final Turn turn;
    private final PlayerInstance playerBlueInstance;
    private final PlayerInstance playerRedInstance;
    private final DeckInstance playerBlueDeckInstance;
    private final DeckInstance playerRedDeckInstance;

    @Singular("blueInstance")
    private Set<CardInstance> blueInstanceSet;
    @Singular("redInstance")
    private Set<CardInstance> redInstanceSet;

    public void summonInstance(UUID playerInstanceId, CardInstance cardInstance) {

        if (playerBlueInstance.getId().equals(playerInstanceId)) {

            Set<CardInstance> set = new HashSet<>();
            set.addAll(blueInstanceSet);
            set.add(cardInstance);
            blueInstanceSet = set;
        } else if (playerRedInstance.getId().equals(playerInstanceId)) {

            Set<CardInstance> set = new HashSet<>();
            set.addAll(redInstanceSet);
            set.add(cardInstance);
            redInstanceSet = set;
        }
    }

    public void buryInstance(CardInstance cardInstance) {

        if (blueInstanceSet.contains(cardInstance)) {

            Set<CardInstance> set = new HashSet<>();
            set.addAll(blueInstanceSet);
            set.remove(cardInstance);
            blueInstanceSet = set;
        } else if (redInstanceSet.contains(cardInstance)) {

            Set<CardInstance> set = new HashSet<>();
            set.addAll(redInstanceSet);
            set.remove(cardInstance);
            redInstanceSet = set;
        }
    }
}
