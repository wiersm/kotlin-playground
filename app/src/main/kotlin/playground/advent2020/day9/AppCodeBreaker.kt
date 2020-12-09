package playground.advent2020.day9

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    val input = FileReader().readLines(resource("/input-advent2020-day9.txt")).map(String::toLong).toList()
    val result = CodeBreaker().findFirstNonMatchingNumber(input, 25)
    UI().printProgramResult("First non-matching number: $result")

    val weakness = CodeBreaker().findEncryptionWeakness(result, input)
    UI().printProgramResult("Encryption weakness: $weakness")
}
