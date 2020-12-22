package playground.advent2020.day22

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class CombatGameTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should return score of winner`() {
        val input = sequenceOf(
            sequenceOf("Player 1:", "9", "2", "6", "3", "1"),
            sequenceOf("Player 2:", "5", "8", "4", "7", "10")
        )

        val winningScore = CombatGame(l).playGame(input)
        assertEquals(306, winningScore)
    }
}
