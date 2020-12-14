package playground.advent2020.day14

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.DEBUG)
    val input = FileReader(l).readLines(resource("/input-advent2020-day14.txt"))
    val result = DockingProgram(l).runInitialisation(input)
    UI().printProgramResult("Sum of address space is: $result")
}
