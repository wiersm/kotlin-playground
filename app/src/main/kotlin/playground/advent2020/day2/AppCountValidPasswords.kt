package playground.advent2020.day2

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    // Count for sled company.
    val lines = FileReader().readLines(resource("/input-advent2020-day2.txt"))
    val nrOfValidPasswords = ValidPasswordCounter().countValidPasswordsForSledCompany(lines)
    UI().printProgramResult("Number of valid passwords for sled company: $nrOfValidPasswords")

    // Count for toboggan company.
    val lines2 = FileReader().readLines(resource("/input-advent2020-day2.txt"))
    val nrOfValidPasswords2 = ValidPasswordCounter().countValidPasswordsForTobogganCompany(lines2)
    UI().printProgramResult("Number of valid passwords for sled company: $nrOfValidPasswords2")
}
