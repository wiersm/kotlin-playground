package playground.advent2020.day13

import playground.utilities.*

fun main() {
    val logger = Logger(LogLevel.DEBUG)
    val inputLines = FileReader(logger).readLines(resource("/input-advent2020-day13.txt")).toList()
    val departTime = inputLines[0].toInt()
    val buses = inputLines[1].splitToSequence(',')

    val answer = ShuttleSearcher(logger).findResultForShortestWaitTime(departTime, buses)

    UI().printProgramResult("Answer for shortest wait time is: $answer")
}
