package be.moac.adventofcode.support

import be.moac.adventofcode.support.ParameterMode.Immediate
import be.moac.adventofcode.support.ParameterMode.Position
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


typealias Memory = Array<String>
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
        while (instructionPointer < memory.size) {
            val instructionCode = memory[instructionPointer++]

            when(instructionCode.takeLast(2).toInt()){
                1 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    val storeParameter = memory[instructionPointer++].toInt()
                    memory[storeParameter] = (parameterOne + parameterTwo).toString()
                }
                2 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    val storeParameter = memory[instructionPointer++].toInt()
                    memory[storeParameter] = (parameterOne * parameterTwo).toString()
                }
                3 -> memory[memory[instructionPointer++].toInt()] = input.receive()
                4 -> output.send(memory[memory[instructionPointer++].toInt()].also { results.add(it) })
                5 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    instructionPointer = if(parameterOne != 0)  parameterTwo else instructionPointer
                }
                6 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    instructionPointer = if(parameterOne == 0)  parameterTwo else instructionPointer
                }
                7 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    memory[memory[instructionPointer++].toInt()]  = if(parameterOne < parameterTwo)  "1" else "0"
                }
                8 -> {
                    val parameterOne = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(1))
                    val parameterTwo = memory.getValue(instructionPointer++, instructionCode.parameterModeFor(2))
                    memory[memory[instructionPointer++].toInt()]  = if(parameterOne == parameterTwo)  "1" else "0"
                }
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

fun Code.asMemory(): Memory =
    this.split(',').toTypedArray()

enum class ParameterMode {
    Position, Immediate
}
