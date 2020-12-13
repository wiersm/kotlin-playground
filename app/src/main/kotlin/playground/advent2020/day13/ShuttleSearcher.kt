package playground.advent2020.day13

import playground.utilities.Logger

class ShuttleSearcher(val l: Logger) {
    fun findResultForShortestWaitTime(earliestDepartureTime: Int, buses: Sequence<String>): Int {
        val busIds = buses.filter { it != "x" }.map(String::toInt).toList()
        l.debug("Bus IDs: $busIds")

        // If you take the remainder of dividing the departure time by the busId, then you get the number of minutes
        // *before* the departure time that the bus last came by. Subtract that from the busId to get the number of
        // minutes before it will come by again.
        val waitTimes = busIds.map { busId -> busId - earliestDepartureTime.rem(busId) }
        l.debug("Wait times: $waitTimes")

        val minWaitTime = waitTimes.min()!!
        val indexOfMinimum = waitTimes.indexOf(minWaitTime)
        val busIdMinWaitTime = busIds[indexOfMinimum]

        val answer = minWaitTime * busIdMinWaitTime
        l.debug("Bus Id $busIdMinWaitTime has best wait time $minWaitTime (product is $answer)")

        return answer
    }
}
