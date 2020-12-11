package playground.advent2019.day3

class PathPlotter {
    fun plot(path: Sequence<String>): BooleanGrid {
        // Determine the size of the required grid
        val gridSizeCalculator = GridSizeCalculator()
        gridSizeCalculator.calculateGridSize(path)

        // Create a grid with the calculated size
        val grid = BooleanGrid(gridSizeCalculator.xmin, gridSizeCalculator.ymin, gridSizeCalculator.xmax, gridSizeCalculator.ymax)

        // Plot the path
        var x = 0
        var y = 0
        path.forEach {
            val move = Move(it)
            if (move.direction == MoveDirection.HORIZONTAL) {
                grid.plotHorizontalLine(x, y, move.steps)
                x += move.steps
            } else {
                grid.plotVerticalLine(x, y, move.steps)
                y += move.steps
            }
        }

        // Return the result
        return grid
    }
}
