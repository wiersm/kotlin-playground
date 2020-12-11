package playground.advent2020.day11

import playground.utilities.*

fun main() {
    val input = FileReader().readLines(resource("/input-advent2020-day11.txt"))
    val seating = SeatingSimulator(Logger(LogLevel.NONE)).findStableSeating(charGridOf(input))
    val nrOfOccupiedSeats = seating.values.map { it.filter { it == '#' }.count() }.sum()
    UI().printProgramResult("Number of occupied seats: $nrOfOccupiedSeats")
}
