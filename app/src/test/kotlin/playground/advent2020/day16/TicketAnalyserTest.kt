package playground.advent2020.day16

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class TicketAnalyserTest {
    val logger = Logger(LogLevel.DEBUG)
    val input = sequenceOf(
        "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
    )

    @Test
    fun `should sum completely incorrect fields`() {
        val sumIncorrectFields = TicketAnalyser(logger).sumIncorrectFields(input)
        assertEquals(71, sumIncorrectFields)
    }
}
