package playground.advent2019.day2

import playground.advent2019.day2.IntCodeProgramRunner
import kotlin.test.Test
import kotlin.test.assertEquals

class IntCodeProgramRunnerTest {
    @Test fun `should run program`() {
        val program = listOf(1,1,1,4,99,5,6,0,99)
        val result = IntCodeProgramRunner().runIntCodeProgram(program)
        val expectedResult = listOf(30,1,1,4,2,5,6,0,99)
        assertEquals(expectedResult, result)
    }
}
