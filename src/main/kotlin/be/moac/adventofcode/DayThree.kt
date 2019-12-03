package be.moac.adventofcode

import kotlin.math.abs

typealias Distance = Int

interface DayThree {

    fun calculateClosestIntersection(pathOne: String, pathTwo: String): Distance

}

class IntersectionCalculator : DayThree {
    private val centralPort = Point(0,0)

    override fun calculateClosestIntersection(pathOne: String, pathTwo: String): Distance {
        val first = pathOne.parse()
        val second = pathTwo.parse()

        return first.intersect(second).filter { point -> point != centralPort }
            .map { point -> abs(centralPort.x - point.x) + abs(centralPort.y - point.y) }
            .min() ?: 0
    }


    private fun String.parse(): Set<Point> {
        val result = mutableSetOf<Point>()

        var position = centralPort

        this.split(",")
            .forEach {
                val direction = it.substring(0, 1)
                val steps = it.substring(1 until it.length).toInt()

                when (direction) {
                    "R" -> {
                        (position.x..position.x + steps).forEach { step ->
                            position = Point(step, position.y)
                            result.add(position)
                        }
                    }
                    "L" -> {
                        (position.x downTo position.x - steps).forEach { step ->
                            position = Point(step, position.y)
                            result.add(position)
                        }
                    }
                    "U" -> {
                        (position.y..position.y + steps).forEach { step ->
                            position = Point(position.x, step)
                            result.add(position)
                        }
                    }
                    "D" -> {
                        (position.y downTo position.y - steps).forEach { step ->
                            position = Point(position.x, step)
                            result.add(position)
                        }
                    }
                }
            }

        return result
    }

}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point =
        Point(this.x + other.x, this.y + other.y)
}
