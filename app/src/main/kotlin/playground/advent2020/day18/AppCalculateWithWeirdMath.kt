package playground.advent2020.day18

import playground.utilities.*

fun main() {
    val expressions = FileReader().readLines(resource("/input-advent2020-day18.txt"))
    val result = expressions.map { expression -> WeirdMathCalculator(Logger(LogLevel.NONE)).evaluate(expression) }.sum()
    UI().printProgramResult("Result of summing all expressions: $result")
}
