package playground.advent2020.day12

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.DEBUG)
    val input = FileReader(l).readLines(resource("/input-advent2020-day12.txt"))
    val distance = ShipSimulator(l).determineDistanceTraveledWithWaypoints(input)
    UI().printProgramResult("Distance traveled: $distance")
}
