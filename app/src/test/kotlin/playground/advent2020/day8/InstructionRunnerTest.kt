package playground.advent2020.day8

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class InstructionRunnerTest {
    private val logger = Logger(LogLevel.DEBUG)
    private val input = sequenceOf("nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6")

    @Test
    fun `should report accumulator before infinite loop`() {
        val instructions = InstructionParser(logger).parse(input)
        val result = InstructionRunner(logger).run(instructions)
        assertEquals(5, result)
    }

    @Test
    fun `should fix program to determine correct accumulator`() {
        val instructions = InstructionParser(logger).parse(input)
        val result = InstructionRunner(logger).fixAndRun(instructions)
        assertEquals(8, result)
    }
}
