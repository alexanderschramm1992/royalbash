package de.schramm.royalbash.domain

fun Game.printLog() {
    println("Game Log:\n${logs.map(Log::print).reduce { log1, log2 -> "$log1\n$log2" }}")
}

private fun Log.print() = " - $id\n   $text"
