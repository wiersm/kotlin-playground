package playground.advent2020.day16

import playground.utilities.*

fun main() {
    val input = FileReader().readLines(resource("/input-advent2020-day16.txt"))
    val result = TicketAnalyser(Logger(LogLevel.NONE)).sumIncorrectFields(input)
    UI().printProgramResult("Sum of invalid fields is: $result")
}
