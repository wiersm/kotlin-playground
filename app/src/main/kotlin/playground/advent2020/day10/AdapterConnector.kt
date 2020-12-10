package playground.advent2020.day10

import playground.utilities.Logger

class AdapterConnector(val l: Logger) {
    fun connectAll(adapterRatings: Sequence<Int>): Int {
        val sortedRatings = adapterRatings.toSortedSet()
        l.debug("The sorted list: $sortedRatings")

        var nrOfOneJoltJumps = 0
        var nrOfThreeJoltJumps = 0
        var previousRating = 0
        sortedRatings.forEach { rating ->
            when (rating - previousRating) {
                1 -> nrOfOneJoltJumps++
                3 -> nrOfThreeJoltJumps++
            }

            previousRating = rating
        }

        // Add one extra for the connection to the charging device.
        nrOfThreeJoltJumps++

        return nrOfOneJoltJumps * nrOfThreeJoltJumps
    }

    fun countPossibleArrangements(adapterRatings: Sequence<Int>): Long {
        // Create a sorted list including the first 0 for the outlet.
        val sortedRatings = sortedSetOf(0)
        sortedRatings.addAll(adapterRatings)
        val ratings = sortedRatings.toList()

        // Go through the list and compute the number of ways to get to each position using results from the
        // previous positions.
        val previousResults = MutableList<Long>(ratings.size) {0}
        for (i in ratings.indices) {
            if (i == 0) {
                l.debug("First element: 1 way to get there")
                previousResults[i] = 1
            } else {
                // The previous adapter is always an option so start with the number of ways it can be reached.
                val previousNrOfWays = previousResults[i - 1]
                l.debug("Next element: start with $previousNrOfWays from previous adapter")
                previousResults[i] = previousNrOfWays
            }

            val minimumRating = ratings[i] - 3

            // If the adapter before that is also an option, add the number of ways that it can be reached.
            if (i > 1 && ratings[i - 2] >= minimumRating) {
                val previousNrOfWays = previousResults[i - 2]
                l.debug("  add $previousNrOfWays from adapter before that")
                previousResults[i] += previousNrOfWays
            }

            // If the adapter before that is also an option, add the number of ways that it can be reached.
            if (i > 2 && ratings[i - 3] >= minimumRating) {
                val previousNrOfWays = previousResults[i - 3]
                l.debug("  add $previousNrOfWays from adapter before that")
                previousResults[i] += previousNrOfWays
            }
        }

        // Return the number of ways that the last adapter can be reached.
        return previousResults.last()
    }
}
