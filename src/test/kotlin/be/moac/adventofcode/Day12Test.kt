package be.moac.adventofcode

import be.moac.adventofcode.DayTwelve.*
import be.moac.adventofcode.DayTwelve.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun `that input is parsed`() {

        //Given:
        val input = """
            <x=-13, y=-13, z=-13>
            <x=5, y=-8, z=3>
            <x=-6, y=-10, z=-3>
            <x=0, y=5, z=-5>
        """.trimIndent()

        //When:
        val result = Moon.of(input)

        //Then:
        assertThat(result)
            .containsExactly(
                Moon(position = Position(x = -13, y=-13, z=-13), velocity = Velocity(x=0, y=0, z=0) ),
                Moon(position = Position(x = 5,   y= -8, z=3),   velocity = Velocity(x=0, y=0, z=0) ),
                Moon(position = Position(x = -6, y=-10, z=-3),   velocity = Velocity(x=0, y=0, z=0) ),
                Moon(position = Position(x = 0, y= 5, z=-5),     velocity = Velocity(x=0, y=0, z=0) )
            )
    }

    @Test
    fun `that calculate velocity of moon is comparing two moons axis the greatest -1 otherwise 1`() {

        //Given:
        val firstMoon = Moon(position = Position(x = -1, y=0,  z=2),   velocity = Velocity(x=0, y=0, z=0))
        val secondMoon = Moon(position = Position(x = 2,  y=-10,z=-7),  velocity = Velocity(x=0, y=0, z=0))

        //When:
        val result = firstMoon gravity secondMoon

        //Then:
        assertThat(result).isEqualTo(Moon(position = Position(x=-1,  y=0, z=2),  velocity = Velocity(x= 1, y=-1, z=-1)))
    }

    @Test
    fun `that moon gravity can be applied by multiple other moons`() {

        //Given:
        val moon = Moon(position = Position(x = -1, y=0,  z=2),   velocity = Velocity(x=0, y=0, z=0))
        val moons = listOf(
            Moon(position = Position(x = 2,  y=-10,z=-7),  velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 4,  y=-8, z=8),   velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 3,  y=5,  z=-1),  velocity = Velocity(x=0, y=0, z=0))
        )

        //When:
        val result = moon move moons

        //Then:
        assertThat(result).isEqualTo(Moon(position = Position(x=2,  y=-1, z= 1),  velocity = Velocity(x= 3, y=-1, z=-1)))
    }

    @Test
    fun `that each moon in list is calculated by each other`() {

        //Given:
        val moons = listOf(
            Moon(position = Position(x = -1, y=0,  z=2),   velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 2,  y=-10,z=-7),  velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 4,  y=-8, z=8),   velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 3,  y=5,  z=-1),  velocity = Velocity(x=0, y=0, z=0))
        )

        //When:
        val result = DayTwelve().run(moons, 1)

        //Then:
        assertThat(result).containsExactly(
            Moon(position = Position(x=2,  y=-1, z= 1),  velocity = Velocity(x= 3, y=-1, z=-1)),
            Moon(position = Position(x=3,  y=-7, z=-4),  velocity = Velocity(x= 1, y= 3, z= 3)),
            Moon(position = Position(x=1,  y=-7, z= 5),  velocity = Velocity(x=-3, y= 1, z=-3)),
            Moon(position = Position(x=2,  y= 2, z= 0),  velocity = Velocity(x=-1, y=-3, z= 1))
        )
    }

    @Test
    fun `that each moon in list is calculated by each other - 10 steps`() {

        //Given:
        val moons = listOf(
            Moon(position = Position(x = -1, y=0,  z=2),   velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 2,  y=-10,z=-7),  velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 4,  y=-8, z=8),   velocity = Velocity(x=0, y=0, z=0)),
            Moon(position = Position(x = 3,  y=5,  z=-1),  velocity = Velocity(x=0, y=0, z=0))
        )

        //When:
        val result = DayTwelve().run(moons, 10)

        //Then:
        assertThat(result).containsExactly(
            Moon(position = Position(x=2,  y= 1, z=-3),  velocity = Velocity(x=-3, y=-2, z= 1)),
            Moon(position = Position(x=1,  y=-8, z= 0),  velocity = Velocity(x=-1, y= 1, z= 3)),
            Moon(position = Position(x=3,  y=-6, z= 1),  velocity = Velocity(x= 3, y= 2, z=-3)),
            Moon(position = Position(x=2,  y= 0, z= 4),  velocity = Velocity(x= 1, y=-1, z=-1))
        )
    }


    @Test
    fun `that potential energy is calculated`() {

        //Expect:
        assertThat(Position(x=2,  y= 1, z=-3).energy).isEqualTo(6)
        assertThat(Position(x=1,  y=-8, z= 0).energy).isEqualTo(9)
        assertThat(Position(x=3,  y=-6, z= 1).energy).isEqualTo(10)
        assertThat(Position(x=2,  y= 0, z= 4).energy).isEqualTo(6)
    }

    @Test
    fun `that kinetic energy is calculated correct`() {

        assertThat(Velocity(x=-3, y=-2, z= 1).energy).isEqualTo(6)
        assertThat(Velocity(x=-1, y= 1, z= 3).energy).isEqualTo(5)
        assertThat(Velocity(x= 3, y= 2, z=-3).energy).isEqualTo(8)
        assertThat(Velocity(x= 1, y=-1, z=-1).energy).isEqualTo(3)

    }

    @Test
    fun `that moon energy is potential energy multiplied by kinetic energy`() {

        assertThat(Moon(position = Position(x=2,  y= 1, z=-3),  velocity = Velocity(x=-3, y=-2, z= 1)).energy).isEqualTo(36)
        assertThat(Moon(position = Position(x=1,  y=-8, z= 0),  velocity = Velocity(x=-1, y= 1, z= 3)).energy).isEqualTo(45)
        assertThat(Moon(position = Position(x=3,  y=-6, z= 1),  velocity = Velocity(x= 3, y= 2, z=-3)).energy).isEqualTo(80)
        assertThat(Moon(position = Position(x=2,  y= 0, z= 4),  velocity = Velocity(x= 1, y=-1, z=-1)).energy).isEqualTo(18)

    }

    @Test
    fun `that total system energy is sum of all moons energy`() {

        assertThat(listOf(Moon(position = Position(x=2,  y= 1, z=-3),  velocity = Velocity(x=-3, y=-2, z= 1)),
            Moon(position = Position(x=1,  y=-8, z= 0),  velocity = Velocity(x=-1, y= 1, z= 3)),
            Moon(position = Position(x=3,  y=-6, z= 1),  velocity = Velocity(x= 3, y= 2, z=-3)),
            Moon(position = Position(x=2,  y= 0, z= 4),  velocity = Velocity(x= 1, y=-1, z=-1))).energy).isEqualTo(179)

    }

    @Test
    fun `other test`() {
        //Given:
        val input = """
            <x=-8, y=-10, z=0>
            <x=5, y=5, z=10>
            <x=2, y=-7, z=3>
            <x=9, y=-8, z=-3>
        """.trimIndent()

        //When:
        val result = DayTwelve().solution(input)

        //Then:
        assertThat(result).isEqualTo(1940)
    }
    @Test
    fun `part one`() {
        //Given:
        val input = """
            <x=-13, y=-13, z=-13>
            <x=5, y=-8, z=3>
            <x=-6, y=-10, z=-3>
            <x=0, y=5, z=-5>
        """.trimIndent()

        //When:
        val result = DayTwelve().solution(input = input, steps = 1000)

        //Then:
        assertThat(result).isEqualTo(8044)
    }
}
