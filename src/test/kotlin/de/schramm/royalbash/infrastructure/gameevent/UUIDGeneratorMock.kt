package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.application.UUIDGenerator

object UUIDGeneratorMock: UUIDGenerator {
    const val MOCK_ID = "MOCK ID"
    override fun generateId() = MOCK_ID
}
