package playground.advent2020.day6

import playground.utilities.Logger

class AnswerProcessor(val l: Logger) {
    fun countUniqueAnswersPerGroup(answers: Sequence<Sequence<String>>): Int {
        return answers.map { answersForGroup ->
            l.debug("Next group: ${answersForGroup.toList()}")
            val uniqueAnswers = answersForGroup
                .map {
                    // Create a set with the answers for this line.
                    it.toCharArray().toSet()
                }
                .reduce { answersSoFar, nextAnswers ->
                    // Reduce the answers by taking the union of the answers.
                    answersSoFar.union(nextAnswers)
                }
            l.debug("Unique answers for this group: ${uniqueAnswers.toList()}")
            uniqueAnswers.size
        }.sum()
    }

    fun countCommonAnswersPerGroup(answers: Sequence<Sequence<String>>): Int {
        return answers.map { answersForGroup ->
            l.debug("Next group: ${answersForGroup.toList()}")
            val commonAnswers = answersForGroup
                .map { answer ->
                    // Create a set with the answers for this line.
                    answer.toCharArray().toSet()
                }
                .reduce { answersSoFar, nextAnswers ->
                    // Reduce the answers by taking the intersection of the answers.
                    answersSoFar.intersect(nextAnswers)
                }

            l.debug("Common answers for group: ${commonAnswers.toList()}")
            commonAnswers.size
        }.sum()
    }

    constructor() : this(Logger())

}
