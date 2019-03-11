package de.schramm.royalbash.domain

import org.springframework.stereotype.Component
import java.util.*

@Component
class UUIDGenerator {

    private fun generateUUID(): UUID {
        return UUID.randomUUID()
    }

    fun generateId(): String {
        return generateUUID().toString()
    }
}
