package de.schramm.royalbash.controller.service.core

import org.springframework.stereotype.Component

import java.util.UUID

@Component
class UUIDGenerator {

    private fun generateUUID(): UUID {
        return UUID.randomUUID()
    }

    fun generateId(): String {
        return generateUUID().toString()
    }
}
