package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DayFiveTest {

    @Test
    fun `that code is split correctly into memory`() {

        //Given:
        val input = "1,2,3,4,5,6,7"

        //When:
        val result = input.parse()

        //Then:
        assertThat(result).isEqualTo(arrayOf("1","2","3","4","5","6","7"))

    }

    @ParameterizedTest
    @CsvSource(value = ["11102,1,Immediate","11102,2,Immediate","01102,3,Position",
        "02,1,Position","02,2,Position","02,3,Position"])
    fun `that parameter mode for paramter at index is going from right to left after opcode`(instructionCode: InstructionCode, index: Int, expected: ParameterMode ) {

        //When:
        val result = instructionCode.parameterModeFor(index)

        //Then:
        assertThat(result).isEqualTo(expected)

    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value =[
        "00001,2,1,0;3,2,1,0",
        "00101,50,1,0;100,50,1,0",
        "1101,50,100,0;150,50,100,0",
        "11101,50,100,0;150,50,100,0",
        "1101,100,-1,0;99,100,-1,0"
    ])
    fun `that opcode 01 will add parameters taken mode in account`(instructions: String, expected: String) {

        //Given:
        val test = TEST(instructions)

        //When:
        test.run("")

        //Then:
        assertThat(test.code).isEqualTo(expected)

    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value =[
        "00002,2,1,0;2,2,1,0",
        "00102,50,1,0;2500,50,1,0",
        "1102,50,100,0;5000,50,100,0",
        "11102,50,100,0;5000,50,100,0"
    ])
    fun `that opcode 02 will mutiply parameters taken mode in account`(instructions: String, expected: String) {

        //Given:
        val test = TEST(instructions)

        //When:
        test.run("")

        //Then:
        assertThat(test.code).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value =[
        "3,0;100;100,0",
        "3,0,3,1,3,2;100;100,100,100,1,3,2"
    ])
    fun `that opcode 03 saves input at position of parameter`(instructions: String, input: String, expected: String) {

        //Given:
        val test = TEST(instructions)

        //When:
        test.run(input)

        //Then:
        assertThat(test.code).isEqualTo(expected)
    }

    @Test
    fun `that opcode 4 will add value at parameter position to outputs`() {

        //Given:
        val test = TEST("3,0,01101,50,60,2,4,0,4,2")

        //When:
        val result = test.run("100")

        //Then:
        assertThat(result).containsExactly("100", "110")
    }

    @Test
    fun `that opcode 99 will stop program and return results`() {

        //Given:
        val test = TEST("3,0,01101,50,60,2,4,0,4,2,99,4,3")

        //When:
        val result = test.run("100")

        //Then:
        assertThat(result).containsExactly("100", "110")

    }

    @Test
    fun `solution part one`() {

        //Given:
        val test = TEST(partOne)

        //When:
        val result = test.run("1")

        //Then:
        println(result)
        println(result.last())

    }

    val partOne = "3,225,1,225,6,6,1100,1,238,225,104,0,1002,114,46,224,1001,224,-736,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,1,166,195,224,1001,224,-137,224,4,224,102,8,223,223,101,5,224,224,1,223,224,223,1001,169,83,224,1001,224,-90,224,4,224,102,8,223,223,1001,224,2,224,1,224,223,223,101,44,117,224,101,-131,224,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,1101,80,17,225,1101,56,51,225,1101,78,89,225,1102,48,16,225,1101,87,78,225,1102,34,33,224,101,-1122,224,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,66,53,224,101,-119,224,224,4,224,102,8,223,223,1001,224,5,224,1,223,224,223,1102,51,49,225,1101,7,15,225,2,110,106,224,1001,224,-4539,224,4,224,102,8,223,223,101,3,224,224,1,223,224,223,1102,88,78,225,102,78,101,224,101,-6240,224,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1107,226,677,224,102,2,223,223,1006,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1005,224,344,101,1,223,223,8,226,677,224,102,2,223,223,1006,224,359,1001,223,1,223,1007,226,677,224,1002,223,2,223,1005,224,374,101,1,223,223,1008,677,677,224,1002,223,2,223,1005,224,389,1001,223,1,223,1108,677,226,224,1002,223,2,223,1006,224,404,1001,223,1,223,1007,226,226,224,1002,223,2,223,1005,224,419,1001,223,1,223,1107,677,226,224,1002,223,2,223,1006,224,434,101,1,223,223,108,677,677,224,1002,223,2,223,1005,224,449,1001,223,1,223,1107,677,677,224,102,2,223,223,1005,224,464,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,479,1001,223,1,223,1008,226,226,224,102,2,223,223,1005,224,494,101,1,223,223,108,677,226,224,102,2,223,223,1005,224,509,1001,223,1,223,8,677,226,224,1002,223,2,223,1006,224,524,101,1,223,223,7,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,554,1001,223,1,223,7,226,226,224,1002,223,2,223,1006,224,569,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,1108,677,677,224,102,2,223,223,1006,224,599,1001,223,1,223,1008,677,226,224,1002,223,2,223,1005,224,614,1001,223,1,223,8,677,677,224,1002,223,2,223,1006,224,629,1001,223,1,223,107,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,1007,677,677,224,102,2,223,223,1006,224,659,101,1,223,223,107,226,226,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226"
}
