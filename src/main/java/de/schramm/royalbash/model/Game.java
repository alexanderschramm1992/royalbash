package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Game {

    private UUID id;
    private Account accountRed;
    private Account accountBlue;
    private Board board;
}
