package be.moac.adventofcode

import be.moac.adventofcode.support.Computer
import be.moac.adventofcode.support.IntComputer
import be.moac.adventofcode.support.Outputs

interface DayFive {

    fun run(vararg input: String = arrayOf("")): Outputs

}

class TEST(instructions: String): DayFive {

    private val computer: Computer = IntComputer(instructions)

    val code: String get() = (computer as IntComputer).code

    override fun run(vararg input: String): Outputs = computer.run(*input)

}


