package playground.advent2019.day3

import kotlin.math.max
import kotlin.math.min

class BooleanGrid(val xmin: Int, val ymin: Int, val xmax: Int, val ymax: Int) {
    val grid: Array<Array<Boolean>> = Array(xmax - xmin + 1) { Array(ymax - ymin + 1) { false } }

    fun plotHorizontalLine(xstart: Int, y: Int, steps: Int) {
        val xend = xstart + steps
        for (x in min(xstart, xend)..max(xstart, xend)) {
            fillLocation(x, y)
        }
    }

    fun plotVerticalLine(x: Int, ystart: Int, steps: Int) {
        val yend = ystart + steps
        for (y in min(ystart, yend)..max(ystart, yend)) {
            fillLocation(x, y)
        }
    }

    private fun fillLocation(x: Int, y: Int) {
        grid[x - xmin][y - ymin] = true
    }

    fun isLocationFilled(x: Int, y: Int): Boolean {
        when {
            x in xmin..xmax && y in ymin..ymax -> return grid[x - xmin][y - ymin]
            else -> return false
        }
    }

    fun forEachPoint(function: (Int, Int) -> Unit) {
        for (x in xmin..xmax) {
            for (y in ymin..ymax) {
                function(x, y)
            }
        }
    }

}
