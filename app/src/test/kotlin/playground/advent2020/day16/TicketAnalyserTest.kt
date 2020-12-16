package playground.advent2020.day16

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class TicketAnalyserTest {
    val logger = Logger(LogLevel.NONE)

    @Test
    fun `should sum completely incorrect fields`() {
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

        val sumIncorrectFields = TicketAnalyser(logger).sumIncorrectFields(input)
        assertEquals(71, sumIncorrectFields)
    }

    @Test
    fun `should multiply departure fields`() {
        val input = sequenceOf(
            "class: 0-1 or 4-19",
            "departure row: 0-5 or 8-19",
            "departure seat: 0-13 or 16-19",
            "",
            "your ticket:",
            "11,12,13",
            "",
            "nearby tickets:",
            "3,9,18",
            "15,1,5",
            "40,4,50",
            "55,2,20",
            "38,6,12",
            "5,14,9"
        )
        val multiplyDepartureFields = TicketAnalyser(logger).multiplyDepartureFields(input)
        assertEquals(11 * 13, multiplyDepartureFields)
    }
}
