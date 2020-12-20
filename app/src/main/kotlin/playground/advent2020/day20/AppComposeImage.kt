package playground.advent2020.day20

import playground.utilities.*

fun main() {
    val logger = Logger(LogLevel.DEBUG)
    val input = FileReader(logger).readBlocksOfLines(resource("/input-advent2020-day20.txt"))
    val result = ImageCompositor(logger).multiplyCornerTiles(input)
    UI().printProgramResult("Result of multiplying ids of corner tiles: $result")
}
