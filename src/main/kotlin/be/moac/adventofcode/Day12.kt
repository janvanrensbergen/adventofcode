package be.moac.adventofcode

import kotlin.math.abs


class DayTwelve {

    fun solutionPartTwo(input: String): Long =
        with(Moon.of(input)) {
            fun Pair<Int, Int>.move(): Pair<Int, Int> = this.first + this.second to this.second

            infix fun Pair<Int, Int>.move(others: List<Pair<Int,Int>>): Pair<Int,Int> =
                others.filterNot { it == this }
                    .fold(this) { result, otherMoon ->
                        result.first to result.second + (result.first comparePosition otherMoon.first) }
                    .move()

            tailrec fun run(moons: List<Pair<Int, Int>>, initialState: List<Pair<Int, Int>>, iterations: Long=0L): Long =
                when (moons.hashCode()) {
                    initialState.hashCode() -> iterations
                    else -> run(moons.map { moon -> moon move moons }, initialState, iterations + 1)
                }

            val x = this.map { it.xPair }
            val y = this.map { it.yPair }
            val z = this.map { it.zPair }

            val xSteps = run(x.map { moon -> moon move x }, x, 1L)
            val ySteps = run(y.map { moon -> moon move y }, y, 1L)
            val zSteps = run(z.map { moon -> moon move z }, z, 1L)

            return (xSteps lcm ySteps) lcm zSteps
        }

    private infix fun Long.lcm(other: Long): Long {
        var a = this
        var b = other
        while (a != 0L) {
            a = (b % a).also { b = a }
        }
        return this / b * other
    }


    fun solutionPartOne(input: String, steps: Int = 100): Int =
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

        val xPair get() = position.x to velocity.x
        val yPair get() = position.y to velocity.y
        val zPair get() = position.z to velocity.z


        infix fun move(others: List<Moon>): Moon =
            others.filterNot { it == this }
                .fold(this) { result, otherMoon -> result gravity otherMoon }
                .move()

        private fun move(): Moon =
            this.copy(position = position apply velocity)

        infix fun gravity(other: Moon): Moon =
            this.copy(
                velocity = Velocity(
                    x = velocity.x + (position.x comparePosition other.position.x),
                    y = velocity.y + (position.y comparePosition other.position.y),
                    z = velocity.z + (position.z comparePosition other.position.z)
                )
            )


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

private infix fun Int.comparePosition(other: Int): Int =
    when {
        this < other -> 1
        this > other -> -1
        this == other -> 0
        else -> this
    }




