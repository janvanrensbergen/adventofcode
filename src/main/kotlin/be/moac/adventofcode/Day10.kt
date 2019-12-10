package be.moac.adventofcode

import kotlin.math.atan2

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
    atan2(other.second.toDouble() - this.second.toDouble() , (this.first.toDouble() - other.first.toDouble()))
