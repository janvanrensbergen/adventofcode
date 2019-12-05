package be.moac.adventofcode

import kotlin.math.floor

typealias Mass = Int
typealias Fuel = Int

interface DayOne {

    fun calculateFuel(vararg mass: Mass): Fuel

}

class FuelCalculator: DayOne {

    override fun calculateFuel(vararg mass: Mass): Fuel =
        mass.map {it.calculateFuel()}.sum()


    private fun Mass.calculateFuel(): Int =
        with(floor(this.toFloat().div(3)).minus(2).toInt()) {
            when {
                this <= 0 -> 0
                else -> this + this.calculateFuel()
            }
        }



}

