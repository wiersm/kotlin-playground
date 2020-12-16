package playground.advent2020.day9

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class CodeBreakerTest {
    private val simpleInput = sequenceOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576").map(String::toLong).toList()

    @Test
    fun `should find first non-matching number`() {
        val result = CodeBreaker(Logger(LogLevel.NONE)).findFirstNonMatchingNumber(simpleInput, 5)
        assertEquals(127, result)
    }

    @Test
    fun `should find encryption weakness`() {
        val result = CodeBreaker(Logger(LogLevel.NONE)).findEncryptionWeakness(127, simpleInput)
        assertEquals(62, result)
    }
}
