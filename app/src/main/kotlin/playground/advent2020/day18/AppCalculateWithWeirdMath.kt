package playground.advent2020.day18

import playground.utilities.*

fun main() {
    val expressions = FileReader().readLines(resource("/input-advent2020-day18.txt"))
    val result = expressions.map { expression -> WeirdMathCalculator(Logger(LogLevel.NONE)).evaluate(expression, false) }.sum()
    UI().printProgramResult("Result of summing all expressions: $result")

    // Once more with precedence
    val expressionsAgain = FileReader().readLines(resource("/input-advent2020-day18.txt"))
    val resultWithPrecedence = expressionsAgain.map { expression -> WeirdMathCalculator(Logger(LogLevel.NONE)).evaluate(expression, true) }.sum()
    UI().printProgramResult("Result of summing all expressions with precedence rules: $resultWithPrecedence")
}
