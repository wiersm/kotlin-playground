package playground.advent2020.day6

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class AnswerProcessorTest {
    val answers = sequenceOf(
        sequenceOf("abc"),
        sequenceOf("a", "b", "c"),
        sequenceOf("ab", "ac"),
        sequenceOf("a", "a", "a", "a"),
        sequenceOf("b"))

    @Test
    fun `should sum number of unique answers per group`() {
        assertEquals(11, AnswerProcessor(Logger(LogLevel.NONE)).countUniqueAnswersPerGroup(answers))
    }

    @Test
    fun `should sum number of common answers per group`() {
        assertEquals(6, AnswerProcessor(Logger(LogLevel.NONE)).countCommonAnswersPerGroup(answers))
    }
}
