package playground.advent2020.day7

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    val rules = FileReader().readLines(resource("/input-advent2020-day7.txt"))
    val nrOfSuitableContainers = LuggageProcessor().countSuitableContainers("shiny gold", rules)
    UI().printProgramResult("Number of suitable colors for containers: $nrOfSuitableContainers")

    val rules2 = FileReader().readLines(resource("/input-advent2020-day7.txt"))
    val nrOfRequiredBags = LuggageProcessor().countRequiredContents("shiny gold", rules2)
    UI().printProgramResult("Number of required bags: $nrOfRequiredBags")
}
