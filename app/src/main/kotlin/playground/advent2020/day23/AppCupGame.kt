package playground.advent2020.day23

import playground.utilities.LogLevel
import playground.utilities.Logger
import playground.utilities.UI

fun main() {
    val l = Logger(LogLevel.INFO)
    val result = CupGame(l).play100Rounds("586439172")
    UI().printProgramResult("Cards after 100 rounds: $result")
}
