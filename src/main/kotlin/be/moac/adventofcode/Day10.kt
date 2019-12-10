package be.moac.adventofcode

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

typealias Input = String

object DayTen {

    fun calculate(input: String): Pair<Int, Int> {
        val astroids = input.parse()
        val result = astroids.map { astroid ->
            astroid to astroids
                .asSequence()
                .filterNot { it == astroid }
                .map { astroid angle it }
                .distinct()
                .count()
        }.toSet().maxBy { it.second }!!

        println(result)
        
        return result.first
    }

    fun shoot(input: String): Int {
        val base = 30 to 34

        val radians = input.parse()
            .asSequence()
            .filterNot { it == base }
            .groupBy { base.angle(it) }
            .map { (key, value) -> key to value.sortedBy { astroid -> base.distance(astroid) }.toMutableList() }
            .sortedByDescending { it.first }

        return (0..199).map {
            radians[it].second.removeAt(0)
        }   .map { it.first * 100 + it.second }
            .last()
            .also { println(it) }
    }
}

fun Input.parse() =
    this.lines()
        .asSequence()
        .map { it.toCharArray() }
        .mapIndexed { y, chars ->
            chars.mapIndexed { x, char ->(x to y) to char }
        }
        .flatten()
        .filter { (_, value) -> value == '#' }
        .map { (key , _) -> key }
        .toList()

infix fun Pair<Int, Int>.angle(other: Pair<Int, Int>) =
    atan2(other.first.toDouble() - this.first.toDouble() , (other.second.toDouble() - this.second.toDouble()))

infix fun Pair<Int, Int>.distance(other: Pair<Int, Int>) =
    sqrt((other.first.toFloat() - this.first.toFloat()).pow(2) + (other.second.toFloat() - this.second.toFloat()).pow(2))
