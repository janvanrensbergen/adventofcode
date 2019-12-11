package be.moac.adventofcode.support

import be.moac.adventofcode.support.ParameterMode.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

typealias Code = String
typealias InstructionCode = String
typealias Outputs = List<String>

interface Computer {

    fun run(vararg inputs: String = arrayOf("")): Outputs

    suspend fun runAsync(input: ReceiveChannel<String>, output: SendChannel<String>): Outputs
}

class IntComputer(instructions: String): Computer {

    val memory = instructions.asMemory()

    val code: String get() = memory.joinToString(separator = ",")

    override fun run(vararg inputs: String): Outputs = runBlocking {
        val inputChannel = Channel<String>(Channel.UNLIMITED)
        val outputChannel = Channel<String>(Channel.UNLIMITED)

        launch {
            for(input in inputs){
                inputChannel.send(input)
            }
        }

        val result = withContext(Dispatchers.Default) { runAsync(inputChannel, outputChannel) }
        outputChannel.close()

        result
    }

    override suspend fun runAsync(input: ReceiveChannel<String>, output: SendChannel<String>): Outputs {
        val results = mutableListOf<String>()
        var instructionPointer = 0
        var base = 0


        fun Memory.index(index: Int, mode: ParameterMode): Int =
            when(mode){
                Position -> this[index].toInt()
                Immediate -> index
                Relative -> base + this[index].toInt()
            }
        fun Memory.getValue(index: Int, mode: ParameterMode): Long =
            this[this.index(index, mode)].toLong()


        while (instructionPointer < memory.size) {
            val instructionCode = memory[instructionPointer++]

            when(instructionCode.takeLast(2).toInt()){
                1 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)

                    memory[memory.index(instructionPointer++, instructionCode.third)] = (parameterOne + parameterTwo).toString()
                }
                2 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)

                    memory[memory.index(instructionPointer++, instructionCode.third)] = (parameterOne * parameterTwo).toString()
                }
                3 -> memory[ memory.index(instructionPointer++, instructionCode.first)] = input.receive().also {
//                    println("Computer received: $it")
                }
                4 -> output.send(memory.getValue(instructionPointer++, instructionCode.first).toString().also {
//                    println("Computer sends: $it")
                    results.add(it)
                })
                5 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)
                    instructionPointer = if(parameterOne != 0L)  parameterTwo.toInt() else instructionPointer
                }
                6 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)
                    instructionPointer = if(parameterOne == 0L)  parameterTwo.toInt() else instructionPointer
                }
                7 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)
                    memory[memory.index(instructionPointer++, instructionCode.third)] = if(parameterOne < parameterTwo)  "1" else "0"
                }
                8 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.first)
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.second)
                    memory[memory.index(instructionPointer++, instructionCode.third)] = if(parameterOne == parameterTwo)  "1" else "0"
                }
                9 -> {
                    base += memory.getValue(instructionPointer++, instructionCode.first).toInt()
                }
                99 -> {
                    output.close()
                    return results
                }
            }
        }

        return results
    }
}

val InstructionCode.first get() = this.parameterModeFor(1)
val InstructionCode.second get() = this.parameterModeFor(2)
val InstructionCode.third get() = this.parameterModeFor(3)

fun InstructionCode.parameterModeFor(parameterIndex: Int): ParameterMode =
    with(this.dropLast(2).reversed()) {
        when{
            this.length < parameterIndex -> Position
            this[parameterIndex-1] =='0' -> Position
            this[parameterIndex-1] =='1' -> Immediate
            this[parameterIndex-1] =='2' -> Relative
            else -> Position
        }
    }

fun Code.asMemory(): Memory =
    Memory(this.split(',').toMutableList())


class Memory(private val values: MutableList<String>): Iterable<String> {

    val size get() = values.size

    operator fun set(index: Int, value: String) {
        values.grow(index + 1)[index] = value
    }

    operator fun get(index: Int): String = values.grow(index + 1)[index]

    private fun MutableList<String>.grow(index: Int) =
        this.apply { if(this.size <= index)
            this.addAll(Array((index) - this.size) {"0"})
        }

    override fun iterator(): Iterator<String> = values.iterator()


}


enum class ParameterMode {
    Position, Immediate, Relative
}
