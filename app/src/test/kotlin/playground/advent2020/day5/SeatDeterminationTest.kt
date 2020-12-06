package playground.advent2020.day5

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class SeatDeterminationTest {
    val logger = Logger(LogLevel.DEBUG)

    @Test
    fun `should determine seat ID from code`() {
        assertEquals(567, determineSeatId("BFFFBBFRRR", logger))
        assertEquals(119, determineSeatId("FFFBBBFRRR", logger))
        assertEquals(820, determineSeatId("BBFFBBFRLL", logger))
    }

    @Test
    fun `should determine max seat ID`() {
        val seatCodes = sequenceOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")
        val maxSeatId = determineMaxSeatId(seatCodes, logger)
        assertEquals(820, maxSeatId)
    }

    @Test
    fun `should find free seat`() {
        val seatIds = listOf(20, 23, 22, 19, 24)
        val freeSeatId = determineFreeSeatId(seatIds)
        assertEquals(21, freeSeatId)
    }
}
