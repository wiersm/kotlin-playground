package playground.advent2020.day15

import playground.utilities.LogLevel
import playground.utilities.Logger
import playground.utilities.UI

fun main() {
    val input = listOf(9,3,1,0,8,4)
    val result = ElfGame(Logger(LogLevel.DEBUG)).playGame(input, 2020)
    UI().printProgramResult("2020th number: $result")

    val longResult = ElfGame(Logger(LogLevel.NONE)).playGame(input, 30000000)
    UI().printProgramResult("30000000th number: $longResult")
}
