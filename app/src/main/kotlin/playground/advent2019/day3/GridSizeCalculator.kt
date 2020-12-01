package playground.advent2019.day3

import kotlin.math.max
import kotlin.math.min

class GridSizeCalculator {
    var xmin = 0
    var ymin = 0
    var xmax = 0
    var ymax = 0

    fun calculateGridSize(path: Sequence<String>) {
        var x = 0
        var y = 0
        path.forEach {
            val move = Move(it)
            if (move.direction == MoveDirection.HORIZONTAL) {
                x += move.steps
            } else {
                y += move.steps
            }
            xmin = min(xmin, x)
            xmax = max(xmax, x)
            ymin = min(ymin, y)
            ymax = max(ymax, y)
        }
    }

}
