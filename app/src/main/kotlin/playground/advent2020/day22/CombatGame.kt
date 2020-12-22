package playground.advent2020.day22

import playground.utilities.Logger

class CombatGame(val l: Logger) {
    fun playGame(inputSequence: Sequence<Sequence<String>>): Long {
        val input = inputSequence.map { lines -> lines.toList() }.toList()
        var cardsPlayer1 = input[0].drop(1).map(String::toInt)
        var cardsPlayer2 = input[1].drop(1).map(String::toInt)
        l.debug("Cards player 1: $cardsPlayer1")
        l.debug("Cards player 2: $cardsPlayer2")

        var round = 0
        do {
            round++

            if (cardsPlayer1[0] > cardsPlayer2[0]) {
                cardsPlayer1 = cardsPlayer1.drop(1).plus(cardsPlayer1[0]).plus(cardsPlayer2[0])
                cardsPlayer2 = cardsPlayer2.drop(1)
            } else {
                cardsPlayer2 = cardsPlayer2.drop(1).plus(cardsPlayer2[0]).plus(cardsPlayer1[0])
                cardsPlayer1 = cardsPlayer1.drop(1)
            }
            l.debug("Cards player 1: $cardsPlayer1")
            l.debug("Cards player 2: $cardsPlayer2")
        } while(cardsPlayer1.isNotEmpty() && cardsPlayer2.isNotEmpty())

        return if (cardsPlayer1.isNotEmpty()) {
            l.info("Player 1 wins after $round rounds: $cardsPlayer1")
            val nrOfCards = cardsPlayer1.size
            cardsPlayer1.mapIndexed { i, card -> card.toLong() * (nrOfCards - i) }.sum()
        } else {
            l.info("Player 2 wins after $round rounds: $cardsPlayer2")
            val nrOfCards = cardsPlayer2.size
            cardsPlayer2.mapIndexed { i, card -> card.toLong() * (nrOfCards - i) }.sum()
        }
    }
}
