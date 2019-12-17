package de.schramm.royalbash.domain

data class Turn(val playerOnTurn: Player,
                val cardDrawn: Boolean = false)
