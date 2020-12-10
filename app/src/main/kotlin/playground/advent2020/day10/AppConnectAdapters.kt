package playground.advent2020.day10

import playground.utilities.*

fun main() {
    val ratings = FileReader().readLines(resource("/input-advent2020-day10.txt")).map(String::toInt)
    val result = AdapterConnector(Logger(LogLevel.NONE)).connectAll(ratings)
    UI().printProgramResult("Answer is: $result")

    val ratings2 = FileReader().readLines(resource("/input-advent2020-day10.txt")).map(String::toInt)
    val nrOfWays = AdapterConnector(Logger(LogLevel.NONE)).countPossibleArrangements(ratings2)
    UI().printProgramResult("Answer is: $nrOfWays")
}
