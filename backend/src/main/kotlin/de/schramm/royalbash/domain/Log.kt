package de.schramm.royalbash.domain

data class Log(val entries: List<Entry>)
data class Entry(val message: String)
