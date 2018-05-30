package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResourcePool {

    private int rations;
    private int material;
    private int blessing;

    public void alterRations(int rations) throws RuleViolationException {

        if(this.rations + rations >= 0) {
            this.rations += rations;
        } else {
            throw new RuleViolationException(String.format("Cannot alter rations by %s", rations));
        }
    }

    public void alterMaterial(int material) throws RuleViolationException {

        if(this.material + material >= 0) {
            this.material += material;
        } else {
            throw new RuleViolationException(String.format("Cannot alter material by %s", material));
        }
    }

    public void alterBlessing(int blessing) throws RuleViolationException {

        if(this.blessing + blessing >= 0) {
            this.blessing += blessing;
        } else {
            throw new RuleViolationException(String.format("Cannot alter blessing by %s", blessing));
        }
    }

    private boolean canSustain(Card card) {

        return rations >= card.getCostRations()
                && material >= card.getCostMaterial()
                && blessing >= card.getCostBlessing();
    }

    public void payFor(Card card) throws RuleViolationException {

        if(canSustain(card)) {

            rations -= card.getCostRations();
            material -= card.getCostMaterial();
            blessing -= card.getCostBlessing();
        } else {
            throw new RuleViolationException(
                    String.format(
                            "Card %s cannot be payed",
                            card.getId()
                    )
            );
        }
    }
}
