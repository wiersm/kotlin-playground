package playground.advent2020.day9

import playground.utilities.Logger

class CodeBreaker(val l: Logger) {
    constructor() : this(Logger())

    fun findFirstNonMatchingNumber(input: List<Long>, preambleSize: Int): Long {
        for (i in preambleSize until input.size) {
            if (!matchesPrecedingNumbers(i, input, preambleSize)) {
                l.debug("Number ${input[i]} at position $i does not match.")
                return input[i]
            }
        }

        l.error("No non-matching number found.")
        return -1
    }

    private fun matchesPrecedingNumbers(index: Int, list: List<Long>, preambleSize: Int): Boolean {
        for (i in index - preambleSize until index)
            for (j in i + 1 until index)
                if (list[i] + list[j] == list[index]) return true

        return false
    }

    fun findEncryptionWeakness(nonMatchingNumber: Long, input: List<Long>): Long {
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                val subList = input.take(j + 1).takeLast(j - i)
                l.debug("Considering sublist $subList")
                val sum = subList.sum()
                if (sum == nonMatchingNumber) return subList.min()!! + subList.max()!!
            }
        }

        l.error("No weakness found.")
        return -1
    }
}
