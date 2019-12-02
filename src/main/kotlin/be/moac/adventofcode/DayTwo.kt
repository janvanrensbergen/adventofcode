package be.moac.adventofcode

interface DayTwo {

    fun run(code: String): String

}

class IntCode: DayTwo {

    override fun run(code: String): String {
        val input = code.split(',').map { it.toInt() }.toMutableList()

        outer@ for (i in input.indices step 4){
            when(input[i]) {
                1 -> input[input[i + 3]] = input[input[i + 1]] + input[input[i + 2]]
                2 -> input[input[i + 3]] = input[input[i + 1]] * input[input[i + 2]]
                99 -> break@outer
            }
        }

        return input.joinToString(separator = ",")
    }

}
