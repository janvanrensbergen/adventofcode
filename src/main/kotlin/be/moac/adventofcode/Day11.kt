package be.moac.adventofcode

import be.moac.adventofcode.support.IntComputer
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import processing.core.PApplet
import java.util.*

typealias Position = Pair<Int, Int>

class DrawingRobot(
    private val instructions: String,
    private val ship: Array<CharArray> = Array(500) { CharArray(500) { '.' } },
    private val startPosition: Position = 250 to 250
) {

    val shipDeque: Queue<Array<CharArray>> = LinkedList<Array<CharArray>>().apply { add(ship.copyOf())}


    fun run(): Int = runBlocking {
        var position: Position = startPosition
        var direction: Direction = Direction.Up

        val paintedPositions = mutableSetOf<Position>()

        val computerInput = Channel<String>(Channel.UNLIMITED).apply {
            when (ship[position.first][position.second]) {
                '.' -> send("0")
                '#' -> send("1")
            }
        }
        val computerOutput = Channel<String>(Channel.UNLIMITED)

        suspend fun draw(input: ReceiveChannel<String>, output: SendChannel<String>) {
            while (!input.isClosedForReceive) {
                paintedPositions.add(position)

                val color = input.receive().toInt()
                val degrees = input.receive().toInt()

                when (color) {
                    0 -> ship[position.first][position.second] = '.'
                    1 -> ship[position.first][position.second] = '#'
                }

                shipDeque.add(ship.copyOf())

                (position step (direction turn degrees).also { direction = it }).also { position = it }

                when (ship[position.first][position.second]) {
                    '.' -> output.send("0")
                    '#' -> output.send("1")
                }
            }
        }

        launch { draw(computerOutput, computerInput) }
        async { IntComputer(instructions).runAsync(computerInput, computerOutput) }.await()

        return@runBlocking paintedPositions.size
    }
}

infix fun Position.step(direction: Direction) =
    when (direction) {
        Direction.Up -> this.first to this.second - 1
        Direction.Left -> this.first - 1 to this.second
        Direction.Down -> this.first to this.second + 1
        Direction.Right -> this.first + 1 to this.second
    }


sealed class Direction {

    abstract infix fun turn(to: Int): Direction

    object Up : Direction() {
        override infix fun turn(to: Int) = when (to) {
            0 -> Left
            1 -> Right
            else -> throw UnsupportedOperationException()
        }
    }

    object Left : Direction() {
        override infix fun turn(to: Int) = when (to) {
            0 -> Down
            1 -> Up
            else -> throw UnsupportedOperationException()
        }
    }

    object Down : Direction() {
        override infix fun turn(to: Int) = when (to) {
            0 -> Right
            1 -> Left
            else -> throw UnsupportedOperationException()
        }
    }

    object Right : Direction() {
        override infix fun turn(to: Int) = when (to) {
            0 -> Up
            1 -> Down
            else -> throw UnsupportedOperationException()
        }
    }
}


fun main() = PApplet.main(App::class.java)

class App : PApplet() {
    private val instructions = "3,8,1005,8,329,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,1002,8,1,29,2,1102,1,10,1,1009,16,10,2,4,4,10,1,9,5,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,0,8,10,4,10,101,0,8,66,2,106,7,10,1006,0,49,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,1002,8,1,95,1006,0,93,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,102,1,8,120,1006,0,61,2,1108,19,10,2,1003,2,10,1006,0,99,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,101,0,8,157,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,1001,8,0,179,2,1108,11,10,1,1102,19,10,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,101,0,8,209,2,108,20,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,101,0,8,234,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1002,8,1,256,2,1102,1,10,1006,0,69,2,108,6,10,2,4,13,10,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,0,10,4,10,1002,8,1,294,1,1107,9,10,1006,0,87,2,1006,8,10,2,1001,16,10,101,1,9,9,1007,9,997,10,1005,10,15,99,109,651,104,0,104,1,21101,387395195796,0,1,21101,346,0,0,1105,1,450,21101,0,48210129704,1,21101,0,357,0,1105,1,450,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,0,46413147328,1,21102,404,1,0,1106,0,450,21102,179355823323,1,1,21101,415,0,0,1105,1,450,3,10,104,0,104,0,3,10,104,0,104,0,21102,1,838345843476,1,21101,0,438,0,1105,1,450,21101,709475709716,0,1,21101,449,0,0,1105,1,450,99,109,2,22102,1,-1,1,21102,40,1,2,21101,0,481,3,21101,0,471,0,1105,1,514,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,476,477,492,4,0,1001,476,1,476,108,4,476,10,1006,10,508,1101,0,0,476,109,-2,2106,0,0,0,109,4,2101,0,-1,513,1207,-3,0,10,1006,10,531,21101,0,0,-3,21201,-3,0,1,21201,-2,0,2,21101,1,0,3,21101,550,0,0,1105,1,555,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,578,2207,-4,-2,10,1006,10,578,21201,-4,0,-4,1105,1,646,22101,0,-4,1,21201,-3,-1,2,21202,-2,2,3,21101,597,0,0,1105,1,555,22102,1,1,-4,21101,0,1,-1,2207,-4,-2,10,1006,10,616,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,638,22102,1,-1,1,21101,638,0,0,106,0,513,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2106,0,0"

    private val robot = DrawingRobot(instructions = instructions,
        ship = Array(500) { CharArray(500) {'.'} },
        startPosition = 250 to 250)

    private var started = false

    override fun settings() {
        size(500 * 2, 500 * 2)
    }

    override fun setup() {
        frameRate(48f)
    }

    override fun keyPressed() {
        if(!started){
            robot.run()
            started = true
        }

        drawShip()
    }

    override fun draw() {

    }

    private fun drawShip() {
        println(robot.shipDeque.size)

        robot.shipDeque.poll()?.let {
            it.forEachIndexed { x, row ->
                row.forEachIndexed { y, cell ->
                    when (cell) {
                        '#' -> fill(255)
                        '.' -> fill(0)
                    }

                    noStroke()
                    rect((x * 2).toFloat(), (y * 2).toFloat(), 2f, 2f)
                }
            }
        }
    }

}
