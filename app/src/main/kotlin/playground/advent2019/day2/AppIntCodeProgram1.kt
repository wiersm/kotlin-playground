package playground.advent2019.day2

import playground.utilities.*

fun main() {
    // Read the program from the input file, converting the input to integers
    val program = FileReader()
        .readCommaSeparatedValues(resource("/input-advent2019-day2.1.txt"))
        .map(String::toInt)
        .toMutableList()

    // Reset the program to the 1202 state
    program[1] = 12
    program[2] = 2

    // Execute the program
    val result = IntCodeProgramRunner(Logger(LogLevel.DEBUG)).runIntCodeProgram(program)

    // Print the result
    UI().printProgramResult("Value at position 0: ${result[0]}")
}
