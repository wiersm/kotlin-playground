package playground.advent2020.day19

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleMatcherTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should count matching messages simplest pattern`() {
        val input = sequenceOf(
            "0: \"a\"",
            "",
            "ababbb",
            "bababa",
            "ab",
            "a",
            "aaaabbb"
        )

        val result = RuleMatcher(l).countMatchingMessages(input)
        assertEquals(1, result)
    }

    @Test
    fun `should count matching messages simple pattern`() {
        val input = sequenceOf(
            "0: 4 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "ab",
            "aaabbb",
            "aaaabbb"
        )

        val result = RuleMatcher(l).countMatchingMessages(input)
        assertEquals(1, result)
    }

    @Test
    fun `should count matching messages for the example`() {
        val input = sequenceOf(
            "1: 2 3 | 3 2",
            "0: 4 1 5",
            "3: 4 5 | 5 4",
            "2: 4 4 | 5 5",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"
        )

        val result = RuleMatcher(l).countMatchingMessages(input)
        assertEquals(2, result)
    }
}
