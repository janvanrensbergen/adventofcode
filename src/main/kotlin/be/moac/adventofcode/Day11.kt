package be.moac.adventofcode

import be.moac.adventofcode.support.IntComputer
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

typealias Position = Pair<Int, Int>

object DrawingRobot {

    fun run(instructions: String): Int = runBlocking {

        val ship = Array(500) { CharArray(500) {'.'} }
        ship[250][250] = '#'
        
        var position: Position = 250 to 250
        var direction: Direction = Direction.Up

        val paintedPositions = mutableSetOf<Position>()

        val computerInput = Channel<String>(Channel.UNLIMITED).apply { send("1") }
        val computerOutput = Channel<String>(Channel.UNLIMITED)

        suspend fun draw(input: ReceiveChannel<String>, output: SendChannel<String>) {
            while(!input.isClosedForReceive){
                paintedPositions.add(position)

                val color = input.receive().toInt()
                val degrees = input.receive().toInt()

                when(color) {
                    0 -> ship[position.first][position.second] = '.'
                    1 -> ship[position.first][position.second] = '#'
                }

                (position step (direction turn degrees).also { direction = it }).also { position = it }

                when(ship[position.first][position.second]) {
                    '.' -> output.send("0")
                    '#' -> output.send("1")
                }
            }
        }

        launch { draw(computerOutput, computerInput) }
        async { IntComputer(instructions).runAsync(computerInput, computerOutput) }.await()

        val result = Array(500) { CharArray(500) {'#'} }

        ship.forEachIndexed { y, cols ->
            cols.forEachIndexed { x, c ->
                when(c) {
                    '#' -> result[x][y] = 'X'
                    else -> result[x][y] = ' '
                }

            }
        }

        result.forEach { println(it.joinToString(separator = "", prefix = " ", postfix = " ")) }


        return@runBlocking paintedPositions.size
    }

}

infix fun Position.step(direction: Direction) =
    when(direction){
        Direction.Up -> this.first to this.second -1
        Direction.Left -> this.first -1 to this.second
        Direction.Down -> this.first to this.second + 1
        Direction.Right -> this.first + 1 to this.second
    }


sealed class Direction {

    abstract infix fun turn(to: Int): Direction

    object Up: Direction() {
        override infix fun turn(to: Int) = when(to) {
            0 -> Left
            1 -> Right
            else -> throw UnsupportedOperationException()
        }
    }

    object Left: Direction() {
        override infix fun turn(to: Int) = when(to) {
            0 -> Down
            1 -> Up
            else -> throw UnsupportedOperationException()
        }
    }

    object Down: Direction() {
        override infix fun turn(to: Int) = when(to) {
            0 -> Right
            1 -> Left
            else -> throw UnsupportedOperationException()
        }
    }

    object Right: Direction() {
        override infix fun turn(to: Int) = when(to) {
            0 -> Up
            1 -> Down
            else -> throw UnsupportedOperationException()
        }
    }

}
