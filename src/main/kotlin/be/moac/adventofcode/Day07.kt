package be.moac.adventofcode

import be.moac.adventofcode.support.IntComputer
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

typealias Output = String

interface DaySeven {

    fun run(): Output

    fun runAsync(): Output

}


class AmplifierControllerSoftware(private val instructions: String) : DaySeven {


    override fun run(): Output =
        generate(min = 0, max = 4)
            .map { run(it) }
            .max()
            .toString()

    override fun runAsync(): Output {
        return generate(min = 5, max = 9)
            .map {
                runAsync(it).toLong() }
            .max()
            .toString()
    }

    fun runAsync(settings: PhaseSettings): Output  = runBlocking {

        val channelA = Channel<String>(Channel.UNLIMITED).apply {
            send(settings.first.toString())
            send(0.toString()) }
        val channelB = Channel<String>(Channel.UNLIMITED).apply { send(settings.second.toString()) }
        val channelC = Channel<String>(Channel.UNLIMITED).apply { send(settings.third.toString()) }
        val channelD = Channel<String>(Channel.UNLIMITED).apply { send(settings.fourth.toString()) }
        val channelE = Channel<String>(Channel.UNLIMITED).apply { send(settings.fifth.toString()) }

        launch { IntComputer(instructions).runAsync(channelA, channelB) }
        launch { IntComputer(instructions).runAsync(channelB, channelC) }
        launch { IntComputer(instructions).runAsync(channelC, channelD) }
        launch { IntComputer(instructions).runAsync(channelD, channelE) }

        val deferred = async { IntComputer(instructions).runAsync(channelE, channelA) }

        val result = deferred.await()
        result.last()
    }

    fun run(settings: PhaseSettings): Output {

        val outputOne = IntComputer(instructions).run(settings.first.toString(), "0")[0]
        val outputTwo = IntComputer(instructions).run(settings.second.toString(), outputOne)[0]
        val outputThree = IntComputer(instructions).run(settings.third.toString(), outputTwo)[0]
        val outputFour = IntComputer(instructions).run(settings.fourth.toString(), outputThree)[0]

        return IntComputer(instructions).run(settings.fifth.toString(), outputFour)[0]
    }
}

fun generate(min: Int = 0, max: Int = 4):List<PhaseSettings>{
    tailrec fun gen(result: List<List<Int>>, index: Int = 0): List<PhaseSettings> {
        return when (index) {
            (max-min) -> result.map { PhaseSettings(it[0], it[1], it[2], it[3], it[4]) }
            else -> {
                gen(result.flatMap { setting -> (min..max).filterNot { setting.contains(it) }.map { setting + mutableListOf(it) }
                }, index + 1)
            }
        }
    }
     return gen((min..max).map { mutableListOf(it) }.toList())
}




data class PhaseSettings(val first: Int, val second: Int, val third: Int, val fourth: Int, val fifth: Int)
