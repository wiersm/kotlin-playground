package playground.advent2020.day18

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class WeirdMathCalculatorTest {
    val l = Logger(LogLevel.NONE)

    @Test
    fun `should calculate simple expression`() {
        assertEquals(71, WeirdMathCalculator(l).evaluate("1 + 2 * 3 + 4 * 5 + 6", false))
    }

    @Test
    fun `should calculate expression with braces`() {
        assertEquals(51, WeirdMathCalculator(l).evaluate("1 + (2 * 3) + (4 * (5 + 6))", false))
    }

    @Test
    fun `should calculate complicated expression`() {
        assertEquals(13632, WeirdMathCalculator(l).evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", false))
    }

    @Test
    fun `should calculate simple expression with operator precedence`() {
        WeirdMathCalculator(l)
        assertEquals(231, WeirdMathCalculator(l).evaluate("1 + 2 * 3 + 4 * 5 + 6", true))
    }

    @Test
    fun `should calculate expression with braces with operator precedence`() {
        WeirdMathCalculator(l)
        assertEquals(51, WeirdMathCalculator(l).evaluate("1 + (2 * 3) + (4 * (5 + 6))", true))
    }

    @Test
    fun `should calculate complicated expression with operator precedence`() {
        WeirdMathCalculator(l)
        assertEquals(23340, WeirdMathCalculator(l).evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true))
    }
}
