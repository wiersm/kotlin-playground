package playground.advent2019.day2

import playground.utilities.*

fun main() {
    // Read the program from the input file, converting the input to integers
    val program = FileReader()
        .readCommaSeparatedValues(resource("/input-advent2019-day2.1.txt"))
        .map(String::toInt)
        .toMutableList()

    // Try out nouns and verbs until we find the requested result.
    var nextNoun = 0
    var nextVerb = 0
    var foundNoun = -1
    var foundVerb = -1
    while (foundNoun < 0 && nextNoun < 100) {
        while (foundVerb < 0 && nextVerb < 100) {
            // Fill the noun and verb in the program
            program[1] = nextNoun
            program[2] = nextVerb

            // Execute the program
            val logger = Logger(LogLevel.INFO)
            val result = IntCodeProgramRunner(logger).runIntCodeProgram(program)

            logger.info("Result for $nextNoun, $nextVerb is: ${result[0]}")

            if (result[0] == 19690720) {
                foundNoun = nextNoun
                foundVerb = nextVerb
                break
            }

            nextVerb += 1
        }
        nextVerb = 0
        nextNoun += 1
    }

    // Print the result
    val result = 100 * foundNoun + foundVerb
    UI().printProgramResult("Answer (100 * noun + verb): $result")
}
