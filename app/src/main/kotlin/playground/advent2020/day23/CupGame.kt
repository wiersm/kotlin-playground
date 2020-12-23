package playground.advent2020.day23

import playground.utilities.Logger

class CupGame(val l: Logger) {
    fun play100Rounds(input: String): String {
        val startingCups = input.map { char -> char.toString().toInt() }.toList()
        l.debug("Starting cups: $startingCups")

        val nextCups = runGame(startingCups, 100, startingCups.size)

        l.info("Cup neighbours at end: $nextCups")

        // Construct the string that we have to return with the cups behind cup 1.
        var cup = 1
        var cupsAfter1 = ""
        repeat(startingCups.size - 1) {
            cup = nextCups[cup]
            cupsAfter1 += cup
        }

        return cupsAfter1
    }

    fun playBigGame(input: String): Long {
        val startingCups = input.map { char -> char.toString().toInt() }.toList()
        l.debug("Starting cups: $startingCups")

        val nextCups = runGame(startingCups, 10_000_000, 1_000_000)

        l.info("Two cups next to 1: ${nextCups[1]} and ${nextCups[nextCups[1]]}")
        return nextCups[1].toLong() * nextCups[nextCups[1]]
    }

    private fun runGame(startingCups: List<Int>, iterations: Int, listSize: Int): List<Int> {
        // Note: solution based on the solution posted by clumsveed on reddit.

        // Create a list that contains the next cup for each cup, starting with ascending numbers.
        val nextCups = MutableList(listSize + 1) { i ->
            when (i) {
                0 -> 0        // 0 is not used
                listSize -> 1 // the last item points back to the start of the list
                else -> i + 1 // the other items point to the next one
            }
        }

        // Update the list with starting configuration.
        startingCups.forEachIndexed { i, cup ->
            when (i) {
                startingCups.size - 1 -> nextCups[cup] = startingCups.size + 1 // The cup to the right of the last starting cup
                else -> nextCups[cup] = startingCups[i + 1]
            }
        }

        // Make sure the correct cup points back to the first of the starting cups.
        if (startingCups.size == listSize) {
            nextCups[startingCups.last()] = startingCups[0]
        } else {
            nextCups[listSize] = startingCups[0] // The cup all the way at the end will point to the first starting cup.
        }

        l.debug("Starting nextCups: $nextCups")

        var currentCup = startingCups[0]

        repeat(iterations) {
            val cupToMove1 = nextCups[currentCup]
            val cupToMove2 = nextCups[cupToMove1]
            val cupToMove3 = nextCups[cupToMove2]
            l.debug("Cups to move: $cupToMove1, $cupToMove2, $cupToMove3")

            var destinationCup = currentCup - 1
            if (destinationCup < 1) destinationCup += listSize
            while (destinationCup == cupToMove1 || destinationCup == cupToMove2 || destinationCup == cupToMove3) {
                destinationCup--
                if (destinationCup < 1) destinationCup += listSize
            }

            nextCups[currentCup] = nextCups[cupToMove3]
            val oldNextCupForDestination = nextCups[destinationCup]
            nextCups[destinationCup] = cupToMove1
            nextCups[cupToMove3] = oldNextCupForDestination

            currentCup = nextCups[currentCup]

            //l.debug("New nextCups: $nextCups")
        }

        return nextCups
    }

}
