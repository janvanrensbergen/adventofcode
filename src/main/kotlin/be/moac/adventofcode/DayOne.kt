package be.moac.adventofcode

interface DayOne {

    fun calculateFuel(vararg mass: Int): Int

}

class FuelCalculator: DayOne {

    override fun calculateFuel(vararg mass: Int): Int =
        mass.map { it.div(3).minus(2) }
            .sum()


}

