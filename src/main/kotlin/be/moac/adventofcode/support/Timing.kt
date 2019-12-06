package be.moac.adventofcode.support

import kotlin.system.measureTimeMillis

fun time(name: String = "", block: () -> Unit) =
    measureTimeMillis(block).also {
        println("$name took $it millis")
    }
