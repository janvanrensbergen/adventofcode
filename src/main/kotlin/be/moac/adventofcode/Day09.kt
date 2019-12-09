package be.moac.adventofcode

import be.moac.adventofcode.support.IntComputer
import be.moac.adventofcode.support.Outputs

object DayNine {

    fun run(instructions: String, input: String = "0"): Outputs =
        IntComputer(instructions).run(input)

}
