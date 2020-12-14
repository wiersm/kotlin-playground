package playground.advent2020.day14

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class DockingProgramTest {
    val logger = Logger(LogLevel.DEBUG)
    val input = sequenceOf("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
        "mem[8] = 11",
        "mem[7] = 101",
        "mem[8] = 0")

    @Test
    fun `should sum address space with bit mask applied`() {
        val result = DockingProgram(logger).runInitialisation(input)
        assertEquals(165, result)
    }
}
