package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class IntCodeTest {

    private val dayTwo = IntCode()

    @Test
    fun `that code 1 will add numbers on given position and stores it at given position`() {

        //Given:
        val input = "1,1,2,3"

        //When:
        val result = dayTwo.run(input)

        //Then:
        assertThat(result).isEqualTo("1,1,2,3")
        
    }

    @Test
    fun `that code 99 will stop the program`() {

        //Given:
        val input = "1,4,5,5,99,1"

        //When:
        val result = dayTwo.run(input)

        //Then:
        assertThat(result).isEqualTo("1,4,5,5,99,100")
    }

    @Test
    fun `that operator 2 will multiply numbers at position of second and thirth number and stores it at position of fourth number`() {

        //Given:
        val input = "2,4,5,5,99,2"

        //When:
        val result = dayTwo.run(input)

        //Then:
        assertThat(result).isEqualTo("2,4,5,5,99,198")
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = [
        "1,9,10,3,2,3,11,0,99,30,40,50;3500,9,10,70,2,3,11,0,99,30,40,50",
        "1,0,0,0,99;2,0,0,0,99",
        "2,3,0,3,99;2,3,0,6,99",
        "2,4,4,5,99,0;2,4,4,5,99,9801",
        "1,1,1,4,99,5,6,0,99;30,1,1,4,2,5,6,0,99"
    ])
    fun `that some more inputs works as expected`(input: String, expected: String) {

        //When:
        val result = dayTwo.run(input)

        //Then:
        assertThat(result).isEqualTo(expected)
    }


    @Test
    fun `the solution`() {

        //Given:
        val input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,2,19,9,23,1,23,5,27,2,6,27,31,1,31,5,35,1,35,5,39,2,39,6,43,2,43,10,47,1,47,6,51,1,51,6,55,2,55,6,59,1,10,59,63,1,5,63,67,2,10,67,71,1,6,71,75,1,5,75,79,1,10,79,83,2,83,10,87,1,87,9,91,1,91,10,95,2,6,95,99,1,5,99,103,1,103,13,107,1,107,10,111,2,9,111,115,1,115,6,119,2,13,119,123,1,123,6,127,1,5,127,131,2,6,131,135,2,6,135,139,1,139,5,143,1,143,10,147,1,147,2,151,1,151,13,0,99,2,0,14,0"

        //When:
        val result = dayTwo.run(input)

        //Then:
        assertThat(result).isEqualTo("4576384,12,2,2,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,24,2,19,9,72,1,23,5,73,2,6,27,146,1,31,5,147,1,35,5,148,2,39,6,296,2,43,10,1184,1,47,6,1186,1,51,6,1188,2,55,6,2376,1,10,59,2380,1,5,63,2381,2,10,67,9524,1,6,71,9526,1,5,75,9527,1,10,79,9531,2,83,10,38124,1,87,9,38127,1,91,10,38131,2,6,95,76262,1,5,99,76263,1,103,13,76268,1,107,10,76272,2,9,111,228816,1,115,6,228818,2,13,119,1144090,1,123,6,1144092,1,5,127,1144093,2,6,131,2288186,2,6,135,4576372,1,139,5,4576373,1,143,10,4576377,1,147,2,4576379,1,151,13,0,99,2,0,14,0")
    }
}
