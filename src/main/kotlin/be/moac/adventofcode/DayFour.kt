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

    private fun Int.onlyDecreaseFromLeftToRight(): Boolean {
        val toCharArray = this.toString().toCharArray()
        for ((index, character) in toCharArray.withIndex()) {
            if(index > 0 && character < toCharArray[index - 1]) {
                return false
            }
        }

        return true
    }

    private fun Int.containsAdjacentSameDigits(): Boolean {
        val toCharArray = this.toString().toCharArray()
        for ((index, character) in toCharArray.withIndex()) {
            if(index > 0 && character == toCharArray[index - 1]) {
                return true
            }
        }

        return false
    }

}


