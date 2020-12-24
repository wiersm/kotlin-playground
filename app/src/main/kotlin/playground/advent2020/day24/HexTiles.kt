package playground.advent2020.day24

import playground.utilities.Logger

class HexTiles(val l: Logger) {
    fun countBlackTiles(input: Sequence<String>): Int {
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

        return blackTiles.size
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
}
