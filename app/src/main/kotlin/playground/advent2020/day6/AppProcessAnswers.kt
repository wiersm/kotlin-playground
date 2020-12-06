package playground.advent2020.day6

import playground.utilities.*

fun main() {
    val answers = FileReader().readBlocksOfLines(resource("/input-advent2020-day6.txt"))
    val count = AnswerProcessor().countUniqueAnswersPerGroup(answers)
    UI().printProgramResult("Sum of answers (unique): $count")

    val answers2 = FileReader().readBlocksOfLines(resource("/input-advent2020-day6.txt"))
    val count2 = AnswerProcessor().countCommonAnswersPerGroup(answers2)
    UI().printProgramResult("Sum of answers (common): $count2")
}
