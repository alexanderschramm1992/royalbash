package de.schramm.royalbash.core.domain.card.effect;

import de.schramm.royalbash.core.domain.game.board.player.ResourcePool;

public interface ResourcePoolAccessor {
    ResourcePool alterRations(int rations);
    ResourcePool alterMaterial(int material);
    ResourcePool alterBlessing(int blessing);
}
