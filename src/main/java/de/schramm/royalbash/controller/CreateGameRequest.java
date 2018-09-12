package de.schramm.royalbash.controller;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class CreateGameRequest {
    private final String accountId1;
    private final String accountId2;
}
