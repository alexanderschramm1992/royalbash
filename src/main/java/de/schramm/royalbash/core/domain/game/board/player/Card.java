package de.schramm.royalbash.core.domain.game.board.player;

import java.util.UUID;

public interface Card {

    UUID getId();
    String getName();
    String getImage();
    String getType();
    String getSubType();
    String getText();
    String getLore();
    int getCostRations();
    int getCostMaterial();
    int getCostBlessing();

    String getCardType();
}