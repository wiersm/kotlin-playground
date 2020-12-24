package playground.advent2020.day24

import playground.utilities.Logger

class HexTiles(val l: Logger) {
    fun countBlackTiles(input: Sequence<String>): Int {
        val blackTiles = determineBlackTileCoordinates(input)

        return blackTiles.size
    }

    private fun determineBlackTileCoordinates(input: Sequence<String>): MutableSet<Pair<Int, Int>> {
        // We'll keep the black tiles in a set.
        val blackTiles = mutableSetOf<Pair<Int, Int>>()

        input.forEach { line ->
            val coordinate = determineCoordinateFor(line)

            // If the coordinate is already black, then we remove it, otherwise we add it.
            if (blackTiles.contains(coordinate)) {
                l.debug("Removing $coordinate for line $line")
                blackTiles.remove(coordinate)
            } else {
                l.debug("Adding $coordinate for line $line")
                blackTiles.add(coordinate)
            }
        }
        return blackTiles
    }

    private fun determineCoordinateFor(line: String): Pair<Int, Int> {
        var toBeProcessed = line
        var x = 0
        var y = 0
        while (toBeProcessed.isNotEmpty()) {
            when(toBeProcessed[0]) {
                'e' -> x++
                'w' -> x--
                'n' -> {
                    if (toBeProcessed[1] == 'e') x++
                    y++
                }
                's' -> {
                    if (toBeProcessed[1] == 'w') x--
                    y--
                }
            }
            when (toBeProcessed[0]) {
                'e', 'w' -> toBeProcessed = toBeProcessed.drop(1)
                'n', 's' -> toBeProcessed = toBeProcessed.drop(2)
            }

        }

        return Pair(x, y)
    }

    fun countBlackTilesAfter100Days(input: Sequence<String>): Int {
        // First determine the coordinates of the initial black tiles again.
        val initialBlackTiles = determineBlackTileCoordinates(input)

        // Create a Hex grid (hopefully 400x400 is big enough: 100 to expand in each direction with room to spare)
        val size = 400
        var hexGrid = HexGrid(size)

        // Set the initial tiles to black
        initialBlackTiles.forEach { coordinate ->
            hexGrid.flip(coordinate.first + 200, coordinate.second + 200)
        }

        repeat(100) {
            val oldHexGrid = hexGrid
            hexGrid = HexGrid(size)
            oldHexGrid.forEachIndexed { x, y, wasBlack ->
                // First set the value if it was set in the previous grid.
                if (wasBlack) hexGrid.setBlack(x, y)

                // Now flip it according to the rules.
                val adjacentBlackTiles = oldHexGrid.adjacentBlackTilesFor(x, y)
                if (wasBlack && (adjacentBlackTiles == 0 || adjacentBlackTiles > 2)) {
                    hexGrid.flip(x, y)
                } else if (!wasBlack && adjacentBlackTiles == 2) {
                    hexGrid.flip(x, y)
                }
            }
        }

        return hexGrid.countBlackTiles()
    }

}

private class HexGrid(val size: Int) {
    val grid = MutableList(size) { MutableList(size) { false } }

    fun flip(x: Int, y: Int) {
        grid[y][x] = !grid[y][x]
    }

    fun forEachIndexed(f: (Int, Int, Boolean) -> Unit) {
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, value ->
                f(x, y, value)
            }
        }
    }

    fun adjacentBlackTilesFor(x: Int, y: Int): Int {
        var result = 0
        // +1, 0
        if (x < size - 1 && grid[y][x + 1]) result++
        // -1, 0
        if (x > 0 && grid[y][x - 1]) result ++
        if (y < size - 1) {
            // +1, +1
            if (x < size - 1 && grid[y + 1][x + 1]) result++
            // 0, +1
            if (grid[y + 1][x]) result++
        }
        if (y > 0) {
            // 0, -1
            if (grid[y - 1][x]) result++
            // -1, -1
            if (x > 0 && grid[y - 1][x - 1]) result++
        }
        return result
    }

    fun setBlack(x: Int, y: Int) {
        grid[y][x] = true
    }

    fun countBlackTiles(): Int {
        // Count all the true values for each row and sum the result.
        return grid.map { row -> row.count { it }}.sum()
    }


}
