package playground.advent2020.day15

import playground.utilities.Logger

class ElfGame(val l: Logger) {
    fun playGame(startingSequence: List<Int>, iterations: Int): Int {
        // Use a map to keep track of the last position of each number.
        val positions = hashMapOf<Int, Int>()

        // Fill the map with the positions of the numbers in the starting sequence except the last one.
        startingSequence.dropLast(1).indices.forEach{ i -> positions[startingSequence[i]] = i }
        l.debug("Positions at start: $positions")

        // Now start the algorithm with looking at the last number in the starting sequence.
        var lastNumber = startingSequence.last()

        // Now we will determine the numbers at position up to 2020 (not including 2020 because zero-based).
        for (i in startingSequence.size until iterations) {
            val positionOfLastNumber = i - 1
            val nextNumber = when (val lastPositionOfLastNumber = positions[lastNumber]) {
                null -> 0
                else -> positionOfLastNumber - lastPositionOfLastNumber
            }
            positions[lastNumber] = positionOfLastNumber
            l.debug("Next number: $nextNumber")
            lastNumber = nextNumber
        }
        return lastNumber
    }
}
