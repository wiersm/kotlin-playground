package playground.advent2020.day17

import playground.utilities.Grid3D
import playground.utilities.Logger

class CubeSimulator(val l: Logger) {
    fun activeCubesAfterSixCycles(input: List<String>): Int {
        val inputWidth = input[0].length
        val inputHeight = input.size

        // Create a 3D grid that is big enough (after six cycles we'll grow at most 6 positions in all directions)
        val xSize = inputWidth + 12
        val ySize = inputHeight + 12
        val zSize = 13

        // Fill the grid with booleans that are true if there is an # in the input
        val grid = Grid3D(xSize, ySize, zSize) { x, y, z ->
            z == 6 && x in 6 until 6 + inputWidth && y in 6 until 6 + inputHeight && input[y - 6][x - 6] == '#'
        }

        l.debug("Grid space created from input:\n${grid.toString { t -> if (t) "#" else "." }}")

        // Update the grid six times
        var newGrid = grid
        repeat(6) {
            val oldGrid = newGrid
            newGrid = Grid3D(xSize, ySize, zSize) { x, y, z ->
                // Count the number of active neighbours in the old grid for each position
                val activeNeighbours = oldGrid.adjacentsFor(x, y, z).filter { it }.count()

                // Make the position active or inactive depending on the number of neighbours
                when (oldGrid.get(x, y, z)) {
                    true -> activeNeighbours == 2 || activeNeighbours == 3
                    false -> activeNeighbours == 3
                }
            }
        }

        l.debug("Grid space after six cycles:\n${newGrid.toString { t -> if (t) "#" else "." }}")

        // Return the number of active cubes in the end result.
        return newGrid.valuesAsSequence().filter { it }.count()
    }
}
