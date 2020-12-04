package playground.advent2020.day4

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    // Validate without checking values.
    val input = FileReader().readLines(resource("/input-advent2020-day4.txt"))
    val nrOfValidPassports = PassportValidator().countValidPassports(input, false)
    UI().printProgramResult("Number of valid passports: $nrOfValidPassports")

    // Validate with checking values.
    val input2 = FileReader().readLines(resource("/input-advent2020-day4.txt"))
    val nrOfValidPassports2 = PassportValidator().countValidPassports(input2, true)
    UI().printProgramResult("Number of valid passports: $nrOfValidPassports2")
}
