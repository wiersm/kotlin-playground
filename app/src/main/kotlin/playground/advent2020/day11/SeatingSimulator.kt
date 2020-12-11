package playground.advent2020.day11

import playground.utilities.Grid
import playground.utilities.Logger

enum class SeatingMethod {
    ADJACENT,
    VISIBLE
}

private const val FLOOR = '.'
private const val EMPTY = 'L'
private const val OCCUPIED = '#'

class SeatingSimulator(val l: Logger) {
    fun findStableSeating(inputGrid: Grid<Char>, seatingMethod: SeatingMethod): Grid<Char> {
        l.debug("Input grid:\n$inputGrid")

        val outputGrid = inputGrid.copy()
        do {
            val previousGrid = outputGrid.copy()
            var seatChanged = false

            outputGrid.forEachCoordinate { x, y ->
                if (changeSeat(x, y, previousGrid, seatingMethod)) {
                    flipSeat(x, y, outputGrid)
                    seatChanged = true
                }
            }
            l.debug("Next iteration:\n$outputGrid")
        } while (seatChanged)

        return outputGrid
    }

    private fun changeSeat(x: Int, y: Int, previousGrid: Grid<Char>, seatingMethod: SeatingMethod): Boolean {
        val currentValue = previousGrid.get(x, y)

        // Floor tiles never change.
        if (currentValue == FLOOR) return false

        // Determine the occupied seats for the chosen method.
        val nrOfOccupiedSeats =
            if (seatingMethod == SeatingMethod.ADJACENT) nrOfOccupiedAdjacentSeats(x, y, previousGrid)
            else nrOfOccupiedVisibleSeats(x, y, previousGrid)

        // Empty seats with no occupied seats around them are flipped.
        if (currentValue == EMPTY && nrOfOccupiedSeats == 0) {
            l.debug("Empty seat at $x, $y with no occupied seats")
            return true
        }

        // For occupied seats it depends on the method and the number of occupied seats.
        if (currentValue == OCCUPIED) {
            if (seatingMethod == SeatingMethod.ADJACENT && nrOfOccupiedSeats >= 4) {
                l.debug("Occupied seat at $x, $y with four or more adjacent occupied seats")
                return true
            } else if (seatingMethod == SeatingMethod.VISIBLE && nrOfOccupiedSeats >= 5) {
                l.debug("Occupied seat at $x, $y with five or more visible occupied seats")
                return true
            }
        }

        return false
    }

    /** Determines the number of directly adjacent occupied seats for a given position. */
    private fun nrOfOccupiedAdjacentSeats(x: Int, y: Int, grid: Grid<Char>) =
        grid.adjacentsFor(x, y).filter { it == OCCUPIED }.count()

    /** Determines the number of visible occupied seats for a given position. */
    private fun nrOfOccupiedVisibleSeats(x: Int, y: Int, grid: Grid<Char>): Int {
        var count = 0
        if (occupiedSeatVisible(x, y, 1, 0, grid)) count++
        if (occupiedSeatVisible(x, y, 1, 1, grid)) count++
        if (occupiedSeatVisible(x, y, 0, 1, grid)) count++
        if (occupiedSeatVisible(x, y, -1, 1, grid)) count++
        if (occupiedSeatVisible(x, y, -1, 0, grid)) count++
        if (occupiedSeatVisible(x, y, -1, -1, grid)) count++
        if (occupiedSeatVisible(x, y, 0, -1, grid)) count++
        if (occupiedSeatVisible(x, y, 1, -1, grid)) count++
        return count
    }

    /** Determines whether an occupied seat is visible from a given position in a given direction. */
    private fun occupiedSeatVisible(fromX: Int, fromY: Int, directionX: Int, directionY: Int, grid: Grid<Char>): Boolean {
        var x = fromX + directionX
        var y = fromY + directionY
        while (x in grid.indicesX && y in grid.indicesY) {
            when (grid.get(x, y)) {
                OCCUPIED -> return true
                EMPTY -> return false
            }
            x += directionX
            y += directionY
        }
        return false
    }

    private fun flipSeat(x: Int, y: Int, grid: Grid<Char>) {
        when (grid.get(x, y)) {
            EMPTY -> grid.set(x, y, OCCUPIED)
            OCCUPIED -> grid.set(x, y, EMPTY)
        }
    }

}

fun countOccupiedSeats(seating: Grid<Char>) =
    seating.values.map { it.filter { it == '#' }.count() }.sum()
