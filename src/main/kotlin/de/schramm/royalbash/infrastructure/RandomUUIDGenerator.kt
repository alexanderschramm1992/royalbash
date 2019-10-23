package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.application.UUIDGenerator
import java.util.*

object RandomUUIDGenerator: UUIDGenerator {
    override fun id(): String {
        return UUID.randomUUID().toString()
    }
}
