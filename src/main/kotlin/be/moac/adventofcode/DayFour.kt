package be.moac.adventofcode

interface DayFour {

    fun findKeys(range: String): List<Int>

}

class KeyGenerator: DayFour {

    override fun findKeys(range: String): List<Int> {
        val split = range.split('-').map { it.toInt() }

        return (split.first() .. split.last())
            .asSequence()
            .filter { it.toString().length == 6 }
            .filter { it.containsAdjacentSameDigits() }
            .filter { it.onlyDecreaseFromLeftToRight() }
            .toList()
    }
}

fun Int.onlyDecreaseFromLeftToRight(): Boolean =
    toString().toCharArray().asSequence()
        .zipWithNext()
        .all { it.first <= it.second}

fun Int.containsAdjacentSameDigits(): Boolean =
    toString().toCharArray()
        .groupBy { it }
        .map { (_, value) -> value.size }
        .any { it == 2 }



