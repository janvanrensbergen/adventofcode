package be.moac.adventofcode.support

fun <T> time(name: String = "", block: () -> T): T {
    val start = System.currentTimeMillis()
    val result = block()
    println("$name took ${System.currentTimeMillis() - start} millis")
    return result
}

