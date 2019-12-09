package be.moac.adventofcode

import be.moac.adventofcode.support.IntComputer

typealias Output = String

interface DaySeven {

    fun run(): Output

}


class AmplifierControllerSoftware(private val instructions: String): DaySeven {


    override fun run(): Output =
        generate((0..4).map { mutableListOf(it) }.toList())
            .map {run(it)}
            .max()
            .toString()

    fun run(settings: PhaseSettings): Output {

        val outputOne = IntComputer(instructions).run(settings.first.toString(), "0")[0]
        val outputTwo = IntComputer(instructions).run(settings.second.toString(), outputOne)[0]
        val outputThree = IntComputer(instructions).run(settings.third.toString(), outputTwo)[0]
        val outputFour = IntComputer(instructions).run(settings.fourth.toString(), outputThree)[0]
        val outputFive = IntComputer(instructions).run(settings.fifth.toString(), outputFour)[0]

        return outputFive
    }
}

fun generate(result: List<List<Int>>, index: Int = 0): List<PhaseSettings> {
    return when (index) {
        4 -> result.map { PhaseSettings(it[0], it[1], it[2], it[3], it[4]) }
        else -> {
            generate(
                result.flatMap { setting ->
                    (0..4)
                        .filterNot { setting.contains(it) }
                        .map {setting + mutableListOf(it)}
                }, index + 1)
        }
    }
}


data class PhaseSettings(val first: Int, val second: Int, val third: Int, val fourth: Int, val fifth: Int)
