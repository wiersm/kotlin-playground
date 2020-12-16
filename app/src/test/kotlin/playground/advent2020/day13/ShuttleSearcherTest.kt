package playground.advent2020.day13

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ShuttleSearcherTest {
    val logger = Logger(LogLevel.NONE)
    val buses = sequenceOf("7", "13", "x", "x", "59", "x", "31", "19")

    @Test
    fun `should find bus with shortest wait time`() {
        val earliestDepartureTime = 939
        val result = ShuttleSearcher(logger).findResultForShortestWaitTime(earliestDepartureTime, buses)
        assertEquals(295, result)
    }

    @Test
    fun `should find special departure time 0`() {
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)
        assertEquals(1068781, result.toLong())
    }

    @Test
    fun `should find special departure time 1`() {
        val buses = "17,x,13,19".splitToSequence(',')
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)
        assertEquals(3417, result.toLong())
    }
    @Test
    fun `should find special departure time 2`() {
        val buses = "67,7,59,61".splitToSequence(',')
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)
        assertEquals(754018, result.toLong())
    }
    @Test
    fun `should find special departure time 3`() {
        val buses = "67,x,7,59,61".splitToSequence(',')
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)
        assertEquals(779210, result.toLong())
    }
    @Test
    fun `should find special departure time 4`() {
        val buses = "67,7,x,59,61".splitToSequence(',')
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)
        assertEquals(1261476, result.toLong())
    }
    @Test
    fun `should find special departure time 5`() {
        val buses2 = sequenceOf("1789", "37", "47", "1889")
        val result = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses2)
        assertEquals(1202161486, result.toLong())
    }
}
