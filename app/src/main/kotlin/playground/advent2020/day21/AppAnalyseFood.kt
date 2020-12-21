package playground.advent2020.day21

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.DEBUG)
    val input = FileReader(l).readLines(resource("/input-advent2020-day21.txt"))
    val result = FoodAnalyser(l).countIngredientsWithoutAllergens(input)
    UI().printProgramResult("Number of ingredients without listed allergens: $result")
}
