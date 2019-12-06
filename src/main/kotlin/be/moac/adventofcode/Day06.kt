package be.moac.adventofcode

interface DaySix {

    fun calculateOrbits(): Int
    fun calculateShortestPath(from: String = "YOU", to: String = "SAN"): Int

}

class OrbitCalculator(input: String): DaySix {

    private val planets = input.parsePlanets()
    private val orbiting = input.parseOrbiting()

    override fun calculateOrbits(): Int {
        fun calculateIndirectOrbits(orbits: List<String>?, sum: Int = 0): Int =
            when {
                orbits.isNullOrEmpty() -> sum
                else -> orbits.size + orbits.map { calculateIndirectOrbits(planets[it]) }.sum()
            }

        val direct = planets.map { (_, value) -> value.size }.sum()
        val indirect = planets.asSequence().map { (_, orbits) -> calculateIndirectOrbits(orbits) }.sum() - direct

        return direct + indirect
    }

    override fun calculateShortestPath(from: String, to: String): Int {

        tailrec fun path(from: String?, path: List<String> = emptyList()): List<String> =
            when (from) {
                null -> path
                else -> path(from = orbiting[from], path = path + listOf(from))
            }

        val fromPath = path(from)
        val toPath = path(to)

        val intersect = fromPath.intersect(toPath).first()

        return (fromPath.indexOf(intersect) + toPath.indexOf(intersect)) -2
    }


    private fun String.parsePlanets() = this.split(",")
        .asSequence()
        .map { it.split(")") }
        .map{ it.first() to it.last() }
        .groupBy { it.first }
        .mapValues { (_, value) -> value.map { it.second }}

    private fun String.parseOrbiting() = this.split(",")
        .asSequence()
        .map { it.split(")") }
        .map{ it.last() to it.first() }
        .toMap()
}


