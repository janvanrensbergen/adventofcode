package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


internal class DayOneTest {

    private val dayOne: DayOne = FuelCalculator()

    @ParameterizedTest
    @CsvSource(value = ["12,2", "14,2", "1969,966", "100756,50346" ]) //
    fun `that fuel is calculated divide by 3 round down and subtract by 2 and for that fuel other fuel is calculated unit zero`(mass: Int, expected: Int) {

        //When:
        val result = dayOne.calculateFuel(mass)

        //Then:
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `that fuel is sum of fuel for each module`() {

        //Given:
        val mass = input

        //When:
        val result = dayOne.calculateFuel(*mass.toIntArray())

        //Then:
        assertThat(result).isEqualTo(5106932)

    }


    private val input = listOf(
        68936,
        53526,
        62556,
        115539,
        119659,
        77887,
        101443,
        71392,
        130327,
        56769,
        55083,
        101448,
        63985,
        60433,
        80302,
        101264,
        134416,
        112047,
        143310,
        73842,
        124020,
        50346,
        124192,
        119547,
        59351,
        122161,
        103742,
        107648,
        132879,
        65047,
        70234,
        54569,
        72785,
        120259,
        134533,
        61778,
        89183,
        144270,
        68600,
        134849,
        120221,
        126887,
        128483,
        101293,
        78066,
        141762,
        101929,
        119173,
        148580,
        142710,
        142029,
        61303,
        133204,
        120872,
        141111,
        124900,
        73600,
        73552,
        138183,
        147019,
        63157,
        127712,
        83610,
        59098,
        101675,
        57951,
        146696,
        135604,
        75158,
        140629,
        106125,
        142451,
        59468,
        69078,
        115676,
        69763,
        129999,
        97987,
        64654,
        104168,
        67894,
        92675,
        125475,
        110450,
        52738,
        87569,
        91939,
        117714,
        121018,
        140534,
        97876,
        146651,
        105741,
        140417,
        74771,
        141727,
        94957,
        145126,
        61429,
        143890
    )
}
