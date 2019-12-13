package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun `run the game`() {

        //Given:

        //When:
        val result = Arcade().partOne()

        //Then:
        assertThat(result).isEqualTo(236)

    }

}
