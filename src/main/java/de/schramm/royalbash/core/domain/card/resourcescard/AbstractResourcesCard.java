package de.schramm.royalbash.core.domain.card.resourcescard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.core.domain.game.board.player.Card;
import de.schramm.royalbash.core.domain.card.effect.GenericEffect;
import de.schramm.royalbash.core.domain.game.board.player.field.ResourcesCard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
abstract class AbstractResourcesCard implements ResourcesCard {

    private final UUID id;
    private final String name;
    private final String image;
    private final String type;
    private final String subType;
    private final String text;
    private final String lore;
    private final int costRations;
    private final int costMaterial;
    private final int costBlessing;

    @JsonIgnore
    private GenericEffect playEffect;

    @Override
    public String getCardType() {
        return "Resources";
    }
}
