package playground.advent2020.day12

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ShipSimulatorTest {
    val logger = Logger(LogLevel.NONE)
    val input = sequenceOf("F10", "N3", "F7", "R90", "F11")

    @Test
    fun `should determine distance traveled`() {
        val distance = ShipSimulator(logger).determineDistanceTraveled(input)
        assertEquals(25, distance)
    }

    @Test
    fun `should determine distance traveled with waypoints`() {
        val distance = ShipSimulator(logger).determineDistanceTraveledWithWaypoints(input)
        assertEquals(286, distance)
    }
}
