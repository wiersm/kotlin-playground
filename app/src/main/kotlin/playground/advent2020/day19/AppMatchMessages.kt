package playground.advent2020.day19

import playground.utilities.*

fun main() {
    val input = FileReader().readLines(resource("/input-advent2020-day19.txt"))
    val result = RuleMatcher(Logger(LogLevel.NONE)).countMatchingMessages(input)
    UI().printProgramResult("Number of matching messages: $result")

    val input2 = FileReader().readLines(resource("/input-advent2020-day19.2.txt"))
    val result2 = RuleMatcher(Logger(LogLevel.NONE)).countMatchingMessages(input2)
    UI().printProgramResult("Number of matching messages with rule substitution: $result2")
}
