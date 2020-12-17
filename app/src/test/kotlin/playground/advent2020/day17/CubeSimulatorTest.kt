package playground.advent2020.day17

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class CubeSimulatorTest {
    val logger = Logger(LogLevel.DEBUG)

    @Test
    fun `should simulate 6 cycles`() {
        val input = listOf(
            ".#.",
            "..#",
            "###"
        )
        val result = CubeSimulator(logger).activeCubesAfterSixCycles(input)
        assertEquals(112, result)
    }
}
