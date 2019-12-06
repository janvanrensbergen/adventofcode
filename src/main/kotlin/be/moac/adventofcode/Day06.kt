package be.moac.adventofcode

interface DaySix {
    fun calculateOrbits(): Int
    fun calculateShortestPath(from: String = "YOU", to: String = "SAN"): Int
}

class OrbitCalculator(input: String): DaySix {
    private val edges = input.parseEdges()

    override fun calculateOrbits()= edges.map { (it, _) -> path(it).size -1 }.sum()
    override fun calculateShortestPath(from: String, to: String)= path(from) shortest path(to)

    private tailrec fun path(from: String?, path: MutableList<String> = mutableListOf()): Collection<String> =
        when (from) {
            null -> path
            else -> path(from = edges[from], path = path.apply { add(from) })
        }

    private infix fun Collection<String>.shortest(other: Collection<String>) =
        ((this union other) subtract (this intersect other)).size -2

    private fun String.parseEdges() =
        this.split(",")
            .asSequence()
            .map { it.split(")") }
            .associate{ it.last() to it.first() }
}


