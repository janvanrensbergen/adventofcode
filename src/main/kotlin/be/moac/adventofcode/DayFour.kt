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
        .fold(mutableListOf<Pair<Char, Int>>()) { acc, c ->
            when {
                acc.isEmpty() -> acc.add(c to 1)
                else ->
                    when (acc.last().first) {
                        c -> acc[acc.size -1] = c to acc.last().second + 1
                        else -> acc.add(c to 1)
                }
            }
            acc
        }.any { it.second == 2 }



