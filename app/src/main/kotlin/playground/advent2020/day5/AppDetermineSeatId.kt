package playground.advent2020.day5

import playground.utilities.FileReader
import playground.utilities.Logger
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    val lines = FileReader().readLines(resource("/input-advent2020-day5.txt"))
    val maxSeatId = determineMaxSeatId(lines, Logger())
    UI().printProgramResult("Max seat ID: $maxSeatId")

    val lines2  = FileReader().readLines(resource("/input-advent2020-day5.txt"))
    val freeSeatId = determineFreeSeatId(determineSeatIds(lines2, Logger()).toList())
    UI().printProgramResult("Free seat ID: $freeSeatId")
}
