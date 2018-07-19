package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.domain.card.summoningcard.Tag;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackingTargetEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget.DefendingTargetEffect;
import de.schramm.royalbash.core.domain.game.board.player.Card;

import java.util.Set;

public interface SummoningCard extends Card {

    AttackingTargetEffect getAttackingTargetEffect();
    DefendingTargetEffect getDefendingTargetEffect();
    int getHealth();
    int getStrength();
    Set<Tag> getTags();
}
