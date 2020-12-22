package playground.advent2020.day22

import playground.utilities.Logger

class CombatGame(val l: Logger) {
    fun playGame(inputSequence: Sequence<Sequence<String>>, recursive: Boolean): Long {
        val input = inputSequence.map { lines -> lines.toList() }.toList()
        val cardsPlayer1 = input[0].drop(1).map(String::toInt)
        val cardsPlayer2 = input[1].drop(1).map(String::toInt)

        val result = playTheGame(cardsPlayer1, cardsPlayer2, recursive)

        return result.score
    }

    private fun playTheGame(startingCardsPlayer1: List<Int>, startingCardsPlayer2: List<Int>, recursive: Boolean): GameResult {
        var cardsPlayer1 = startingCardsPlayer1
        var cardsPlayer2 = startingCardsPlayer2

        l.debug("Starting cards player 1: $cardsPlayer1")
        l.debug("Starting cards player 2: $cardsPlayer2")

        var round = 0
        val previousRounds = mutableSetOf<String>()
        do {
            // Infinite game prevention rule: break if this round is the same as any previous round
            val stringForRound = stringForRound(cardsPlayer1, cardsPlayer2)
            if (previousRounds.contains(stringForRound)) break

            round++
            previousRounds.add(stringForRound)

            if (player1Wins(cardsPlayer1, cardsPlayer2, recursive)) {
                cardsPlayer1 = cardsPlayer1.drop(1).plus(cardsPlayer1[0]).plus(cardsPlayer2[0])
                cardsPlayer2 = cardsPlayer2.drop(1)
            } else {
                cardsPlayer2 = cardsPlayer2.drop(1).plus(cardsPlayer2[0]).plus(cardsPlayer1[0])
                cardsPlayer1 = cardsPlayer1.drop(1)
            }
            l.debug("Cards player 1: $cardsPlayer1")
            l.debug("Cards player 2: $cardsPlayer2")
        } while (cardsPlayer1.isNotEmpty() && cardsPlayer2.isNotEmpty())

        // Return the result of who won. Note that player1 wins by default (infinite game prevention rule)
        return if (cardsPlayer1.isNotEmpty()) {
            l.info("Player 1 wins after $round rounds: $cardsPlayer1")
            val nrOfCards = cardsPlayer1.size
            GameResult(
                cardsPlayer1.mapIndexed { i, card -> card.toLong() * (nrOfCards - i) }.sum(),
                true
            )
        } else {
            l.info("Player 2 wins after $round rounds: $cardsPlayer2")
            val nrOfCards = cardsPlayer2.size
            GameResult(
                cardsPlayer2.mapIndexed { i, card -> card.toLong() * (nrOfCards - i) }.sum(),
                false
            )
        }
    }

    private fun player1Wins(cardsPlayer1: List<Int>, cardsPlayer2: List<Int>, recursive: Boolean): Boolean {
        // If we're not playing a recursive game or if either player does not have enough cards for a recursive game,
        // then the player with the highest card wins.
        if (!recursive || cardsPlayer1.size <= cardsPlayer1[0] || cardsPlayer2.size <= cardsPlayer2[0]) {
            return cardsPlayer1[0] > cardsPlayer2[0]
        } else {
            val resultOfSubGame = playTheGame(
                cardsPlayer1.drop(1).take(cardsPlayer1[0]),
                cardsPlayer2.drop(1).take(cardsPlayer2[0]),
                true)
            return resultOfSubGame.player1Wins
        }
    }

    private fun stringForRound(cardsPlayer1: List<Int>, cardsPlayer2: List<Int>): String =
        cardsPlayer1.joinToString(",") + "|" + cardsPlayer2.joinToString(",")
}

private class GameResult(val score: Long, val player1Wins: Boolean)
