package playground.advent2020.day23

import playground.utilities.Logger

class CupGame(val l: Logger) {
    fun play100Rounds(input: String): String {
        val startingCups = input.map { char -> char.toString().toInt() }.toList()
        l.debug("Starting cups: $startingCups")

        var cups = startingCups
        var currentCupIndex = 0

        repeat (100) {
            val currentCupValue = cups[currentCupIndex]

            val cupsToMove = cupsToRightOf(currentCupIndex, cups)
            l.debug("Cups to move: $cupsToMove")

            val cupsWithout = cups.minus(cupsToMove)
            val destinationCup = cupsWithout.filter { cup -> cup < currentCupValue }.max() ?: (cupsWithout.max())
            val destinationIndex = cupsWithout.indexOf(destinationCup) + 1 // Plus one because we place next to destination cup

            cups = cupsWithout.take(destinationIndex).plus(cupsToMove).plus(cupsWithout.drop(destinationIndex))
            currentCupIndex = (cups.indexOf(currentCupValue) + 1) % cups.size
            l.debug("New cups: $cups, current cup: ${cups[currentCupIndex]}")
        }

        l.info("Cups at end: $cups")

        val indexOf1 = cups.indexOf(1)
        return cups.drop(indexOf1 + 1).joinToString("") + cups.take(indexOf1).joinToString("")
    }

    private fun cupsToRightOf(currentCupIndex: Int, cups: List<Int>): List<Int> = List(3) { i ->
        cups[(currentCupIndex + 1 + i) % cups.size]
    }


}
