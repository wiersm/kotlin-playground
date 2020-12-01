package playground.advent2019.day3

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource
import kotlin.math.abs

fun main() {
    // Read the input
    val lines = FileReader().readLinesWithCommaSeparatedValues(resource("/input-advent2019-day3.txt")).toList()

    // Plot both paths on a grid
    val grid1 = PathPlotter().plot(lines[0])
    val grid2 = PathPlotter().plot(lines[1])

    // Look for intersections and determine the one closest to the central port
    var minimumDistance = Int.MAX_VALUE
    var xFound = 0
    var yFound = 0
    grid1.forEachPoint {
        x: Int, y: Int ->

        val distance = abs(x) + abs(y)
        if (x != 0 && y != 0 && grid1.isLocationFilled(x, y) && grid2.isLocationFilled(x, y) && distance < minimumDistance ) {
            minimumDistance = distance
            xFound = x
            yFound = y
        }
    }

    // Report the answer
    UI().printProgramResult("Distance of nearest intersection ($xFound, $yFound): $minimumDistance")
}
