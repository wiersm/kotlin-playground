package playground.advent2020.day14

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.NONE)
    val input = FileReader(l).readLines(resource("/input-advent2020-day14.txt"))
    val result = DockingProgram(l).runInitialisationVersion2(input)
    UI().printProgramResult("Sum of address space is: $result")
}
