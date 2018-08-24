package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.card.effect.ResourcePoolAccessor;
import de.schramm.royalbash.core.domain.game.board.player.field.Card;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.val;

@Getter
@Builder
public class ResourcePool implements ResourcePoolAccessor {

    private final int rations;
    private final int material;
    private final int blessing;

    public ResourcePool alterRations(int rations) {
        return new ResourcePool(this.rations + rations, material, blessing);
    }

    public ResourcePool alterMaterial(int material) {
        return new ResourcePool(rations, this.material + material, blessing);
    }

    public ResourcePool alterBlessing(int blessing) {
        return new ResourcePool(rations, material, this.blessing + blessing);
    }

    boolean canSustain(Card card) {

        return rations >= card.getCostRations()
                && material >= card.getCostMaterial()
                && blessing >= card.getCostBlessing();
    }

    ResourcePool payFor(Card card) {

        val rations = this.rations - card.getCostRations();
        val material = this.material - card.getCostMaterial();
        val blessing = this.blessing - card.getCostBlessing();

        return new ResourcePool(rations, material, blessing);
    }
}
