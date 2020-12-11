package playground.advent2019.day3

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BooleanGridTest {
    @Test fun `should start empty`() {
        val xmin = -1
        val ymin = -1
        val xmax = 9
        val ymax = 8
        val grid = BooleanGrid(xmin, ymin, xmax, ymax)
        for (x in xmin..xmax) {
            for (y in ymin..ymax) {
                assertFalse(grid.isLocationFilled(x, y))
            }
        }
    }

    @Test fun `locations outside the grid are empty`() {
        val grid = BooleanGrid(-1, -1, 9, 8)
        assertFalse(grid.isLocationFilled(-2, -2))
        assertFalse(grid.isLocationFilled(100, 10000))
    }

    @Test fun `should plot horizontal lines`() {
        val grid = BooleanGrid(-1, -1, 9, 8)

        grid.plotHorizontalLine(8, 5, -5)

        assertTrue(grid.isLocationFilled(8, 5), "start of line")
        assertTrue(grid.isLocationFilled(6, 5), "middle of line")
        assertTrue(grid.isLocationFilled(3, 5), "end of line")

        assertFalse(grid.isLocationFilled(2, 5), "outside start of line")
        assertFalse(grid.isLocationFilled(9, 5), "beyond end of line")

        assertFalse(grid.isLocationFilled(6, 4), "underneath line")
    }

    @Test fun `should plot vertical lines`() {
        val grid = BooleanGrid(-1, -1, 9, 8)

        grid.plotVerticalLine(8, 5, -5)

        assertTrue(grid.isLocationFilled(8, 0), "start of line")
        assertTrue(grid.isLocationFilled(8, 3), "middle of line")
        assertTrue(grid.isLocationFilled(8, 5), "end of line")

        assertFalse(grid.isLocationFilled(8, -1), "under start of line")
        assertFalse(grid.isLocationFilled(8, 6), "beyond end of line")

        assertFalse(grid.isLocationFilled(9, 4), "right of line")
    }
}
