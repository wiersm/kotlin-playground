package playground.advent2020.day11

import playground.utilities.Grid
import playground.utilities.Logger

class SeatingSimulator(val l: Logger) {
    fun findStableSeating(inputGrid: Grid<Char>): Grid<Char> {
        l.debug("Input grid:\n$inputGrid")

        val outputGrid = inputGrid.copy()
        do {
            val previousGrid = outputGrid.copy()
            var seatChanged = false

            outputGrid.forEachCoordinate { x, y ->
                if (changeSeat(x, y, previousGrid)) {
                    flipSeat(x, y, outputGrid)
                    seatChanged = true
                }
            }
            l.debug("Next iteration:\n$outputGrid")
        } while (seatChanged)

        return outputGrid
    }

    private val FLOOR = '.'
    private val EMPTY = 'L'
    private val OCCUPIED = '#'

    private fun changeSeat(x: Int, y: Int, previousGrid: Grid<Char>): Boolean {
        val currentValue = previousGrid.get(x, y)
        if (currentValue == FLOOR) return false

        if (currentValue == EMPTY && nrOfOccupiedAdjacentSeats(x, y, previousGrid) == 0) {
            l.debug("Empty seat at $x, $y with no adjacent occupied seats")
            return true
        }

        if (currentValue == OCCUPIED && nrOfOccupiedAdjacentSeats(x, y, previousGrid) >= 4) {
            l.debug("Occupied seat at $x, $y with four or more adjacent occupied seats")
            return true
        }

        return false
    }

    private fun nrOfOccupiedAdjacentSeats(x: Int, y: Int, grid: Grid<Char>) =
        grid.adjacentsFor(x, y).filter { it == OCCUPIED }.count()

    private fun flipSeat(x: Int, y: Int, grid: Grid<Char>) {
        when (grid.get(x, y)) {
            EMPTY -> grid.set(x, y, OCCUPIED)
            OCCUPIED -> grid.set(x, y, EMPTY)
        }
    }

}
