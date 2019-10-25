package de.schramm.royalbash.domain

fun Game.logCreatureRemoved(uuidGenerator: UUIDGenerator, creature: Creature): Game =
        log(uuidGenerator, "Removed ${creature.name} from the battlefield")
