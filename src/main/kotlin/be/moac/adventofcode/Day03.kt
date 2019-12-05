@file:JvmName("Day04Kt")

package be.moac.adventofcode

import kotlin.math.abs

typealias Distance = Int
typealias Steps = Int
typealias Path = String

private val centralPort = Point(0, 0)

interface DayThree {
    fun calculateShortestDistance(pathOne: Path, pathTwo: Path): Distance
    fun calculateMinSteps(pathOne: Path, pathTwo: Path): Steps
}

class IntersectionCalculator : DayThree {

    override fun calculateShortestDistance(pathOne: Path, pathTwo: Path): Distance =
        Grid(pathOne, pathTwo).calculate {
            it.intersections.map { point -> abs(centralPort.x - point.x) + abs(centralPort.y - point.y) }.min() ?: 0
        }

    override fun calculateMinSteps(pathOne: Path, pathTwo: Path): Steps =
        Grid(pathOne, pathTwo).calculate {
            it.intersections.map { point -> it.first.indexOf(point) + it.second.indexOf(point) }.min() ?: 0
        }
}

class Grid(pathOne: Path, pathTwo: Path) {
    val first = pathOne.parse()
    val second = pathTwo.parse()
    val intersections = first.intersect(second).filter { it != centralPort }

    fun <T> calculate(block: (Grid) -> T): T {
        return block(this)
    }

    private fun Path.parse(): List<Point> =
        split(",")
        .fold(mutableListOf(centralPort)) { result, it ->
            (1..it.steps()).forEach { _ ->
                when (it.direction()) {
                    "R" -> result.add(result.last().stepRight())
                    "L" -> result.add(result.last().stepLeft())
                    "U" -> result.add(result.last().stepUp())
                    "D" -> result.add(result.last().stepDown())
                    else -> throw RuntimeException("Unknown direction")
                }
            }
            result
        }

    private fun String.direction(): String = this.substring(0, 1)
    private fun String.steps(): Int = this.substring(1 until this.length).toInt()
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(this.x + other.x, this.y + other.y)

    fun stepLeft() = this + Point(-1, 0)
    fun stepRight() = this + Point(1, 0)
    fun stepUp() = this + Point(0, 1)
    fun stepDown() = this + Point(0, -1)
}
