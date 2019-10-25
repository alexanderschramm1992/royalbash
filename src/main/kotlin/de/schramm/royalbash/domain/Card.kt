package de.schramm.royalbash.domain

interface Card {

    val id: String
    val instanceId: String
    val name: String
    val text: String
    val cost: Int
    val image: String

    operator fun invoke(context: InvokationOnSpotContext): Game

    operator fun invoke(context: InvokationOnPlayerContext): Game

    operator fun invoke(context: InvokationOnCreatureContext): Game
}
