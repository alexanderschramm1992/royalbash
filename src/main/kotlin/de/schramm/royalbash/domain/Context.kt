package de.schramm.royalbash.domain

import de.schramm.royalbash.application.UUIDGenerator

data class Context(
        val uuidGenerator: UUIDGenerator,
        val game: Game,
        val ownerId: String,
        val targetPlayerId: String? = null,
        val targetSpotId: String? = null,
        val targetCreatureInstanceId: String? = null)
