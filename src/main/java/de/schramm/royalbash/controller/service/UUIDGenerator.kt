package de.schramm.royalbash.controller.service

import org.springframework.stereotype.Component

import java.util.UUID

@Component
class UUIDGenerator {

    fun generateUUID(): UUID {
        return UUID.randomUUID()
    }
}
