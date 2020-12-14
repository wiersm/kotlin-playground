package playground.advent2020.day14

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class DockingProgramTest {
    val logger = Logger(LogLevel.DEBUG)

    @Test
    fun `should sum address space with bit mask applied`() {
        val input = sequenceOf("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0")
        val result = DockingProgram(logger).runInitialisation(input)
        assertEquals(165, result)
    }

    @Test
    fun `should sum address space for version 2`() {
        val input = sequenceOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
        )
        val result = DockingProgram(logger).runInitialisationVersion2(input)
        assertEquals(208, result)
    }
}
