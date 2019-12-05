package be.moac.adventofcode

import be.moac.adventofcode.ParameterMode.Immediate
import be.moac.adventofcode.ParameterMode.Position

typealias Memory = Array<String>
typealias Code = String
typealias InstructionCode = String
typealias Outputs = List<String>

interface DayFive {

    fun run(input: String): Outputs

}

class TEST(instructions: String): DayFive {

    val memory = instructions.parse()

    val code: String get() = memory.joinToString(separator = ",")

    override fun run(input: String): Outputs {
        val results = mutableListOf<String>()

        var index = 0
        while (index < memory.size) {
            val instructionCode = memory[index++]

            when(instructionCode.takeLast(2).toInt()){
                 1 -> {
                    val parameterOne = memory.getValue(index++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(index++, instructionCode.parameterModeFor(2))

                    val storeParameter = memory[index++].toInt()

                    memory[storeParameter] = (parameterOne + parameterTwo).toString()
                }
                2 -> {
                    val parameterOne = memory.getValue(index++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(index++, instructionCode.parameterModeFor(2))

                    val storeParameter = memory[index++].toInt()

                    memory[storeParameter] = (parameterOne * parameterTwo).toString()
                }
                3 -> memory[memory[index++].toInt()] = input
                4 -> results.add(memory[memory[index++].toInt()])
                99 -> return results
            }
        }

        return results
    }
}

fun Memory.getValue(index: Int, mode: ParameterMode): Int =
    when(mode){
        Position -> this[this[index].toInt()].toInt()
        Immediate -> this[index].toInt()
    }

fun InstructionCode.parameterModeFor(parameterIndex: Int): ParameterMode =
    with(this.dropLast(2).reversed()) {
        when{
            this.length < parameterIndex -> Position
            this[parameterIndex-1] =='0' -> Position
            this[parameterIndex-1] =='1' -> Immediate
            else -> Position
        }
    }

fun Code.parse(): Memory =
    this.split(',').toTypedArray()

enum class ParameterMode {
    Position, Immediate
}
