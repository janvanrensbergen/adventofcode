package be.moac.adventofcode

import kotlin.math.abs


class DayTwelve {

    fun solution(input: String, steps: Int = 100): Int =
        run(Moon.of(input), steps).energy

    fun run(moons: List<Moon>, times: Int) =
        run(moons, 0, times)

    private tailrec fun run(moons: List<Moon>, index:Int = 0, iterations: Int): List<Moon> =
        when (index) {
            iterations -> moons
            else -> run(moons.map { moon -> moon move moons }, index + 1, iterations)
        }


    data class Moon(val position: Position, val velocity: Velocity) {
        val energy: Int get() = position.energy * velocity.energy

        infix fun move(others: List<Moon>): Moon =
            others.filterNot { it == this }
                .fold(this) { result, otherMoon -> result gravity otherMoon }
                .move()

        private fun move(): Moon =
            this.copy(position = position apply velocity)

        infix fun gravity(other: Moon): Moon =
            this.copy(
                velocity = Velocity(
                    x = velocity.x + (position.x compare other.position.x),
                    y = velocity.y + (position.y compare other.position.y),
                    z = velocity.z + (position.z compare other.position.z)
                )
            )

        private infix fun Int.compare(other: Int): Int =
            when {
                this < other -> 1
                this > other -> -1
                this == other -> 0
                else -> this
            }

        companion object {
            fun of(input: String): List<Moon> =
                input.lines()
                    .asSequence()
                    .map { it.removePrefix("<").removeSuffix(">").split(",") }
                    .map {
                        val x = it[0].trim().removePrefix("x=").toInt()
                        val y = it[1].trim().removePrefix("y=").toInt()
                        val z = it[2].trim().removePrefix("z=").toInt()
                        Position(x, y, z)
                    }
                    .map { Moon(position = it, velocity = Velocity(0, 0, 0)) }
                    .toList()

        }
    }

    data class Position(val x: Int, val y: Int, val z: Int) {

        val energy: Int get() = abs(x).plus(abs(y)).plus(abs(z))

        infix fun apply(velocity: Velocity): Position =
            Position(
                x = this.x + velocity.x,
                y = this.y + velocity.y,
                z = this.z + velocity.z
            )

    }

    data class Velocity(val x: Int, val y: Int, val z: Int) {
        val energy: Int get() = abs(x).plus(abs(y)).plus(abs(z))
    }
}

val List<DayTwelve.Moon>.energy get() = this.sumBy{ it.energy }




