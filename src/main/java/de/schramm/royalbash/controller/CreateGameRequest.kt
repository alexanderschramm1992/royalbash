package de.schramm.royalbash.controller

data class CreateGameRequest(
        val accountId1: String,
        val accountId2: String
) {
    constructor(): this(accountId1 = "", accountId2 = "")
}
