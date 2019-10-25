package de.schramm.royalbash.domain

data class AttackCreatureContext(val uuidGenerator: UUIDGenerator,
                                 val game: Game,
                                 val owner: Player,
                                 val defender: Creature)

data class AttackPlayerContext(val uuidGenerator: UUIDGenerator,
                               val game: Game,
                               val owner: Player,
                               val defender: Player)

data class AttackSpotContext(val uuidGenerator: UUIDGenerator,
                             val game: Game,
                             val owner: Player,
                             val defender: Spot)
