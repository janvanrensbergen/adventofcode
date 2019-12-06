package be.moac.adventofcode

interface DaySix {

    fun calculateOrbits(input: String): Int

}

class OrbitCalculator: DaySix {

    override fun calculateOrbits(input: String): Int {
        val planets = input.parse()
        val direct = planets.map { (_, value) -> value.size }.sum()
        val indirect = planets.map { (_, orbits) -> calculateIndirectOrbits(orbits, planets) }.sum() - direct

        return direct + indirect
    }

    private fun calculateIndirectOrbits(
        orbits: List<String>?,
        planets: Map<String, List<String>>
    ): Int = when {
        orbits.isNullOrEmpty() -> 0
        else -> orbits.size + orbits.map { calculateIndirectOrbits(planets[it], planets) }.sum()
    }


    fun String.parse() = this.split(",")
        .asSequence()
        .map { it.split(")") }
        .map { it.first() to it.last() }
        .groupBy { it.first }
        .mapValues { (_, value) -> value.map { it.second }}
}


