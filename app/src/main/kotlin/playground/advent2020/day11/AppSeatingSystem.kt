package playground.advent2020.day11

import playground.utilities.*

fun main() {
    val inputGrid = charGridOf(FileReader().readLines(resource("/input-advent2020-day11.txt")))

    val seating = SeatingSimulator(Logger(LogLevel.NONE)).findStableSeating(inputGrid, SeatingMethod.ADJACENT)
    val nrOfOccupiedSeats = countOccupiedSeats(seating)
    UI().printProgramResult("Number of occupied seats with adjacent method: $nrOfOccupiedSeats")

    val seating2 = SeatingSimulator(Logger(LogLevel.NONE)).findStableSeating(inputGrid, SeatingMethod.VISIBLE)
    val nrOfOccupiedSeats2 = countOccupiedSeats(seating2)
    UI().printProgramResult("Number of occupied seats with visible method: $nrOfOccupiedSeats2")
}
