package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun `run the game - part one`() {
        //When:
        val result = Arcade().partOne()

        //Then:
        assertThat(result).isEqualTo(236)

    }
    @Test
    fun `run the game - part two`() {
        //When:
        val result = Arcade().partTwo()

        //Then:
        assertThat(result).isEqualTo(11040)

    }

}
