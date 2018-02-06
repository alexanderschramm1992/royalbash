package de.schramm.royalbash.model.card.instance;

import java.util.Set;

public interface CanEquip extends CardInstance {

    Set<Equippable> getEquippedSet();
}
