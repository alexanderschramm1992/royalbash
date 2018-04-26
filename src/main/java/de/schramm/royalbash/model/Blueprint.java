package de.schramm.royalbash.model;

import de.schramm.royalbash.model.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Blueprint {

    private final UUID id;
    private String name;

    @Singular("card")
    private List<Card> cards;
}
