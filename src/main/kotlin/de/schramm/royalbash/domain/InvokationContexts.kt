package de.schramm.royalbash.domain

data class InvokationOnCreatureContext(val uuidGenerator: UUIDGenerator,
                                       val game: Game,
                                       val owner: Player,
                                       val target: Creature)

data class InvokationOnPlayerContext(val uuidGenerator: UUIDGenerator,
                                     val game: Game,
                                     val owner: Player,
                                     val target: Player)

data class InvokationOnSpotContext(val uuidGenerator: UUIDGenerator,
                                   val game: Game,
                                   val owner: Player,
                                   val target: Spot)
