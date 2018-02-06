package de.schramm.royalbash.model;

import de.schramm.royalbash.model.player.Player;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Game {

    private UUID id;
    private Player playerRed;
    private Player playerBlue;
    private Board board;
}
