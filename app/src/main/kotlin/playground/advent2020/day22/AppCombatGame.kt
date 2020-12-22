package playground.advent2020.day22

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.INFO)
    val input = FileReader(l).readBlocksOfLines(resource("/input-advent2020-day22.txt"))
    val score = CombatGame(l).playGame(input)
    UI().printProgramResult("Score of winning player: $score")
}
