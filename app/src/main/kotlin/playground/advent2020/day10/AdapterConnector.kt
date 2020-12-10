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

        nrOfThreeJoltJumps++

        return nrOfOneJoltJumps * nrOfThreeJoltJumps
    }
}
