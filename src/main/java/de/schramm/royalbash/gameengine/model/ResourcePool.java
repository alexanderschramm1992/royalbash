package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResourcePool {

    private int rations;
    private int material;
    private int blessing;

    public void addRations(int rations) {

        this.rations += rations;
    }

    private boolean canSustain(Card card) {

        return rations >= card.getCostRations()
                && material >= card.getCostMaterial()
                && blessing >= card.getCostBlessing();
    }

    public void payFor(Card card) throws GameRuleViolationException {

        if(canSustain(card)) {

            rations -= card.getCostRations();
            material -= card.getCostMaterial();
            blessing -= card.getCostBlessing();
        } else {
            throw new GameRuleViolationException(
                    String.format(
                            "Card %s cannot be payed",
                            card.getId()
                    )
            );
        }
    }
}
