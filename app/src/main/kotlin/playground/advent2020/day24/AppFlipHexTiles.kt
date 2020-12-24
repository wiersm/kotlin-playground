package playground.advent2020.day24

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.DEBUG)
    val input = FileReader(l).readLines(resource("/input-advent2020-day24.txt"))
    val nrOfBlackTiles = HexTiles(l).countBlackTiles(input)
    UI().printProgramResult("Number of black tiles: $nrOfBlackTiles")

    val inputAgain = FileReader(l).readLines(resource("/input-advent2020-day24.txt"))
    val nrOfBlackTilesAfter100Days = HexTiles(l).countBlackTilesAfter100Days(inputAgain)
    UI().printProgramResult("Number of black tiles after 100 days: $nrOfBlackTilesAfter100Days")
}
