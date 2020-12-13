package playground.advent2020.day13

import playground.utilities.*

fun main() {
    val logger = Logger(LogLevel.DEBUG)
    val inputLines = FileReader(logger).readLines(resource("/input-advent2020-day13.txt")).toList()
    val buses = inputLines[1].splitToSequence(',')

    val answer = ShuttleSearcher(logger).findDepartureTimeMatchingPattern(buses)

    UI().printProgramResult("Earliest departure time matching pattern: $answer")
}
