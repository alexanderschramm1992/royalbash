package de.schramm.royalbash.model.player;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.AttackableCanAttack;
import de.schramm.royalbash.model.card.instance.CardInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.*;

@Builder
@Getter
public class PlayerInstance implements AttackableCanAttack {

    private static final InstanceType instanceType = InstanceType.Player;

    private final UUID id;
    private final Player player;

    private int currentHealth;

    @Singular("handCardInstance")
    private List<CardInstance> handCardInstanceList;

    @Override
    public int getCurrentStrength() {
        return 0; // Players have no attack damage
    }

    @Override
    public int getCost() {

        return 0; // Players have no cost
    }

    @Override
    public String getName() {

        return player.getName();
    }

    @Override
    public InstanceType getInstanceType() {

        return instanceType;
    }

    @Override
    public boolean isDead() {

        return currentHealth <= 0;
    }

    @Override
    public void receiveDamage(AttackableCanAttack attackingInstance) {

        currentHealth -= attackingInstance.getCurrentStrength();
    }

    public void addCardInstanceToHand(CardInstance cardInstance) {

        List<CardInstance> list = new ArrayList<>();

        list.addAll(handCardInstanceList);

        list.add(cardInstance);

        handCardInstanceList = list;
    }

    public void removeCardInstanceFromHand(CardInstance cardInstance) {

        List<CardInstance> list = new ArrayList<>();

        list.addAll(handCardInstanceList);

        list.remove(cardInstance);

        handCardInstanceList = list;
    }
}
