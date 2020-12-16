package playground.advent2020.day11

import playground.utilities.LogLevel
import playground.utilities.Logger
import playground.utilities.charGridOf
import kotlin.test.Test
import kotlin.test.assertEquals

class SeatingSimulatorTest {
    private val input = sequenceOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL")

    @Test
    fun `should find stable seating arrangement adjacent`() {
        val inputGrid = charGridOf(input)
        val outputGrid = SeatingSimulator(Logger(LogLevel.NONE)).findStableSeating(inputGrid, SeatingMethod.ADJACENT)

        val expectedOutput = charGridOf(sequenceOf(
            "#.#L.L#.##",
            "#LLL#LL.L#",
            "L.#.L..#..",
            "#L##.##.L#",
            "#.#L.LL.LL",
            "#.#L#L#.##",
            "..L.L.....",
            "#L#L##L#L#",
            "#.LLLLLL.L",
            "#.#L#L#.##"
        ))

        assertEquals(expectedOutput, outputGrid)
    }

    @Test
    fun `should find stable seating arrangement visible`() {
        val inputGrid = charGridOf(input)
        val outputGrid = SeatingSimulator(Logger(LogLevel.NONE)).findStableSeating(inputGrid, SeatingMethod.VISIBLE)

        val expectedOutput = charGridOf(sequenceOf(
            "#.L#.L#.L#",
            "#LLLLLL.LL",
            "L.L.L..#..",
            "##L#.#L.L#",
            "L.L#.LL.L#",
            "#.LLLL#.LL",
            "..#.L.....",
            "LLL###LLL#",
            "#.LLLLL#.L",
            "#.L#LL#.L#"
        ))

        assertEquals(expectedOutput, outputGrid)
    }
}
