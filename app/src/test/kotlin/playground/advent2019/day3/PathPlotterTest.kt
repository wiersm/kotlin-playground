package playground.advent2019.day3

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PathPlotterTest {
    @Test fun `should plot path`() {
        val path = sequenceOf("R8", "U5", "L5", "D3")

        val grid = PathPlotter().plot(path)

        // Check the origin
        assertTrue(grid.isLocationFilled(0, 0))

        // Check some points of the first R8
        assertTrue(grid.isLocationFilled(4, 0))
        assertTrue(grid.isLocationFilled(8, 0))

        // Check the end point of the U5
        assertTrue(grid.isLocationFilled(8, 5))

        // Check a random other point.
        assertTrue(grid.isLocationFilled(3, 3))

        // Check a random other point that should be false.
        assertFalse(grid.isLocationFilled(4, 3))
    }
}
