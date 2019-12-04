package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test

internal class DayFourTest {

    private val dayFour = KeyGenerator()

    @Test
    fun `that key is a six digit number`() {

        //Given:
        val range = "172851-675869"

        //When:
        val result = dayFour.findKeys(range)

        //Then:
        assertSoftly { softly ->
            result.forEach {
                softly.assertThat(it.toString().length).isEqualTo(6)
            }
        }

    }

    @Test
    fun `that key is whitin given range`() {

        //Given:
        val range = "172851-675869"

        //When:
        val result = dayFour.findKeys(range)

        //Then:
        assertSoftly { softly ->
            result.forEach {
                assertThat(it).isGreaterThan(172851).isLessThan(675869)
            }
        }


    }

    @Test
    fun `that there must be two adjacent digits the same`() {

        //Given:
        val range = "172851-675869"

        //When:
        val result = dayFour.findKeys(range)

        //Then:
        assertSoftly { softly ->
            result.forEach {
                softly.assertThat(it.containsAdjacentSameDigits()).isTrue
            }
        }


    }

    @Test
    fun `that key going from left to right never decrease`() {

        //Given:
        val range = "172851-675869"

        //When:
        val result = dayFour.findKeys(range)

        //Then:
        assertSoftly { softly ->
            result.forEach {
                softly.assertThat(it.onlyDecreaseFromLeftToRight()).isTrue()
            }
        }
    }

    @Test
    fun `that posible keys are correct`() {

        //Given:
        val range = "172851-675869"

        //When:
        val result = dayFour.findKeys(range)

        //Then:
        assertThat(result).hasSize(1135)

    }


}
