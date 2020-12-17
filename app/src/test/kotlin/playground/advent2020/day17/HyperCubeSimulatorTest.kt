package playground.advent2020.day17

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class HyperCubeSimulatorTest {
    val logger = Logger(LogLevel.NONE)

    @Test
    fun `should simulate 6 cycles`() {
        val input = listOf(
            ".#.",
            "..#",
            "###"
        )
        val result = HyperCubeSimulator(logger).activeCubesAfterSixCycles(input)
        assertEquals(848, result)
    }
}
