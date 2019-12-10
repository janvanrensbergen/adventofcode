package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test  {

    @Test
    fun `calculate the angle`() {
        assertThat((15 to 8) angle (10 to 7)).isEqualTo(-20)
        
    }


    @Test
    fun `that input can be parsed correctly`() {
        //Given:
        val map = """ 
            .#..#
            .....
            #####
            ....#
            ...##
        """.trimIndent()

        //When:
        val result = map.parse()

        //Then:
        assertThat(result).containsExactly(
            1 to 0,
            4 to 0,
            0 to 2,
            1 to 2,
            2 to 2,
            3 to 2,
            4 to 2,
            4 to 3,
            3 to 4,
            4 to 4
        )
    }

    @Test
    fun `that monitoring station is correct calculated`() {

        //Given:
        val map = """ 
            .#..#
            .....
            #####
            ....#
            ...##
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 3 to 4)
    }

    @Test
    fun `should be 11,13`() {

        //Given:
        val map = """ 
            .#..##.###...#######
            ##.############..##.
            .#.######.########.#
            .###.#######.####.#.
            #####.##.#.##.###.##
            ..#####..#.#########
            ####################
            #.####....###.#.#.##
            ##.#################
            #####.##.###..####..
            ..######..##.#######
            ####.##.####...##..#
            .#####..#.######.###
            ##...#.##########...
            #.##########.#######
            .####.#.###.###.#.##
            ....##.##.###..#####
            .#.#.###########.###
            #.#.#.#####.####.###
            ###.##.####.##.#..##
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 11 to 13)
    }

    @Test
    fun `should be 5,8`() {

        //Given:
        val map = """ 
            ......#.#.
            #..#.#....
            ..#######.
            .#.#.###..
            .#..#.....
            ..#....#.#
            #..#....#.
            .##.#..###
            ##...#..#.
            .#....####
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 5 to 8)
    }

    @Test
    fun `should be 1,2`() {

        //Given:
        val map = """ 
            #.#...#.#.
            .###....#.
            .#....#...
            ##.#.#.#.#
            ....#.#.#.
            .##..###.#
            ..#...##..
            ..##....##
            ......#...
            .####.###.
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 1 to 2)
    }
    @Test
    fun `should be 6,3`() {

        //Given:
        val map = """ 
            .#..#..###
            ####.###.#
            ....###.#.
            ..###.##.#
            ##.##.#.#.
            ....###..#
            ..#.#..#.#
            #..#.#.###
            .##...##.#
            .....#.#..
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 6 to 3)
    }

    @Test
    fun solution() {

        //Given:
        val map = """ 
....#.....#.#...##..........#.......#......
.....#...####..##...#......#.........#.....
.#.#...#..........#.....#.##.......#...#..#
.#..#...........#..#..#.#.......####.....#.
##..#.................#...#..........##.##.
#..##.#...#.....##.#..#...#..#..#....#....#
##...#.............#.#..........#...#.....#
#.#..##.#.#..#.#...#.....#.#.............#.
...#..##....#........#.....................
##....###..#.#.......#...#..........#..#..#
....#.#....##...###......#......#...#......
.........#.#.....#..#........#..#..##..#...
....##...#..##...#.....##.#..#....#........
............#....######......##......#...#.
#...........##...#.#......#....#....#......
......#.....#.#....#...##.###.....#...#.#..
..#.....##..........#..........#...........
..#.#..#......#......#.....#...##.......##.
.#..#....##......#.............#...........
..##.#.....#.........#....###.........#..#.
...#....#...#.#.......#...#.#.....#........
...####........#...#....#....#........##..#
.#...........#.................#...#...#..#
#................#......#..#...........#..#
..#.#.......#...........#.#......#.........
....#............#.............#.####.#.#..
.....##....#..#...........###........#...#.
.#.....#...#.#...#..#..........#..#.#......
.#.##...#........#..#...##...#...#...#.#.#.
#.......#...#...###..#....#..#...#.........
.....#...##...#.###.#...##..........##.###.
..#.....#.##..#.....#..#.....#....#....#..#
.....#.....#..............####.#.........#.
..#..#.#..#.....#..........#..#....#....#..
#.....#.#......##.....#...#...#.......#.#..
..##.##...........#..........#.............
...#..##....#...##..##......#........#....#
.....#..........##.#.##..#....##..#........
.#...#...#......#..#.##.....#...#.....##...
...##.#....#...........####.#....#.#....#..
...#....#.#..#.........#.......#..#...##...
...##..............#......#................
........................#....##..#........#
        """.trimIndent()

        //When:
        val result = DayTen.calculate(map)

        //Then:
        assertThat(result).isEqualTo( 30 to 34)

    }
}
