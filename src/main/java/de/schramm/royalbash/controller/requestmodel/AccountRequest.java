package de.schramm.royalbash.controller.requestmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountRequest {

    private String name;
    private String email;
    private String passwordHash;
}
