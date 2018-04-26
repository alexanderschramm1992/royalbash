package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class Account {

    private UUID id;
    private String name;
    private String email;
    private String passwordHash;

    @Singular("blueprint")
    private Set<Blueprint> blueprints;
}
