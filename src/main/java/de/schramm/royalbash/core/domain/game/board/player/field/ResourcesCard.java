package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.domain.card.effect.GenericEffect;

public interface ResourcesCard extends Card {
    GenericEffect getPlayEffect();
}
