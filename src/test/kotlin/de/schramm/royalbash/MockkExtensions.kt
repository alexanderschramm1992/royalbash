package de.schramm.royalbash

import io.mockk.MockKVerificationScope
import io.mockk.verify

fun verifyThat(description: String, blockToVerify: MockKVerificationScope.() -> Unit) = try {
    verify(verifyBlock = blockToVerify)
} catch (error: AssertionError) {
    throw AssertionError("Condition [$description] not met due to: \n ${error.message}", error)
}
