package playground.advent2020.day13

import playground.utilities.Logger
import java.math.BigInteger

class ShuttleSearcher(val l: Logger) {
    fun findResultForShortestWaitTime(earliestDepartureTime: Int, buses: Sequence<String>): Int {
        val busIds = buses.filter { it != "x" }.map(String::toInt).toList()
        l.debug("Bus IDs: $busIds")

        val waitTimes = busIds.map { busId -> waitTimeFor(busId, earliestDepartureTime) }
        l.debug("Wait times: $waitTimes")

        val minWaitTime = waitTimes.min()!!
        val indexOfMinimum = waitTimes.indexOf(minWaitTime)
        val busIdMinWaitTime = busIds[indexOfMinimum]

        val answer = minWaitTime * busIdMinWaitTime
        l.debug("Bus Id $busIdMinWaitTime has best wait time $minWaitTime (product is $answer)")

        return answer
    }

    // If you take the remainder of dividing the departure time by the busId, then you get the number of minutes
    // *before* the departure time that the bus last came by. Subtract that from the busId to get the number of
    // minutes before it will come by again.
    private fun waitTimeFor(busId: Int, earliestDepartureTime: Int) = busId - earliestDepartureTime.rem(busId)

    fun findDepartureTimeMatchingPattern(buses: Sequence<String>): BigInteger {
        /*
         We are looking for t such that
         -  t.rem(b1) equals 0
         -  b2 - t.rem(b2) equals 1
         -  etc

         t is a multiple of b1
         t = n * b1
         So for the second bus we are looking for n such that
         b2 - (n * b1).rem(b2) equals 1
         or in other words that n * b1 + 1 is a multiple of b2

         e.g. 3 and 7
         6, 7 is a solution
         27 and 28 is a solution
         48 and 49 also
         => so we start at 6 and repeats every b1 * b2 = 21 steps

         e.g. 3, 7 and 2
         6, 7, 8 is a solution
         48, 49, 50
         now it starts at 6 and repeats every b1 * b2 * b3 = 42 steps

         or 3, 7, x and 2
         27, 28, 30 is a solution
         69, 70, 72
         now it starts at 27 and also repeats every b1 * b2 * b3 = 42 steps

         In other words: start with the first bus (step = id) and try to match one more bus (new step is id times bigger).
         => should actually use least common multiple instead of multiplying? does that matter for given input? => apparently not, but let's do it just for fun
         */

        // Convert the buses to integers to make it easier (we will ignore the zeroes).
        val busIds = buses.map{ if (it == "x") "0" else it }.map(String::toInt).toList()

        // Start at t = 0
        var t = BigInteger.ZERO
        var step = busIds[0].toBigInteger() // We can safely assume the first one is not an x, see input file.

        for (i in 1 until busIds.size) {
            val busId = busIds[i]
            if (busId == 0) continue

            l.debug("Next busId: $busId, current step size $step, looking for offset $i")

            // Jump forward until we find a matching time.
            // We are looking for a time t such that t + i is a multiple of the busId
            do {
                t += step
            } while ((t + i.toBigInteger()).rem(busId.toBigInteger()) != BigInteger.ZERO)

            l.debug("Matching time for buses up to index $i: $t")

            // Calculate the next step size by taking the least common multiple of the current step size and the busId.
            step = step * busId.toBigInteger() / step.gcd(busId.toBigInteger())
        }

        return t
    }
}
