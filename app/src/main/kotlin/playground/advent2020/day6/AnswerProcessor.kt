package playground.advent2020.day6

import playground.utilities.Logger

class AnswerProcessor(val l: Logger) {
    fun countUniqueAnswersPerGroup(answers: Sequence<Sequence<String>>): Int {
        var count = 0
        answers.forEach { answersForGroup ->
            l.debug("Processing a group")
            val uniqueAnswers = mutableSetOf<Char>()
            answersForGroup.forEach { answer ->
                l.debug("Processing answers: $answer")
                uniqueAnswers.addAll(answer.toCharArray().toSet())
            }
            count += uniqueAnswers.size
        }
        return count
    }

    fun countCommonAnswersPerGroup(answers: Sequence<Sequence<String>>): Int {
        var count = 0
        answers.forEach { answersForGroup ->
            val commonAnswers = mutableSetOf<Char>()
            var isFirst = true
            answersForGroup.forEach { answer ->
                l.debug("Processing answers: $answer")
                val nextAnswers = answer.toCharArray().toSet()
                if (isFirst) {
                    commonAnswers.addAll(nextAnswers)
                    isFirst = false
                } else {
                    commonAnswers.removeIf { c -> !nextAnswers.contains(c) }
                }
            }
            l.debug("Common answers for group: ${commonAnswers.toList()}")
            count += commonAnswers.size
        }
        return count
    }

    constructor() : this(Logger())

}
