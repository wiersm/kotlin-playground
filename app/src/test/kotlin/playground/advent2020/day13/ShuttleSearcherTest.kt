package playground.advent2020.day13

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ShuttleSearcherTest {
    val logger = Logger(LogLevel.DEBUG)
    val earliestDepartureTime = 939
    val buses = sequenceOf("7", "13", "x", "x", "59", "x", "31", "19")

    @Test
    fun `should find bus with shortest wait time`() {
        val result = ShuttleSearcher(logger).findResultForShortestWaitTime(earliestDepartureTime, buses)
        assertEquals(295, result)
    }
}
