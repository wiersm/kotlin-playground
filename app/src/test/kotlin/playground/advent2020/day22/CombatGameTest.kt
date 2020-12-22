package playground.advent2020.day22

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class CombatGameTest {
    val l = Logger(LogLevel.NONE)

    val exampleInput = sequenceOf(
        sequenceOf("Player 1:", "9", "2", "6", "3", "1"),
        sequenceOf("Player 2:", "5", "8", "4", "7", "10")
    )

    @Test
    fun `should return score of winner`() {
        val winningScore = CombatGame(l).playGame(exampleInput, false)
        assertEquals(306, winningScore)
    }

    @Test
    fun `should support recursive game`() {
        val winningScore = CombatGame(l).playGame(exampleInput, true)
        assertEquals(291, winningScore)
    }

    @Test
    fun `should prevent infinite loop`() {
        val input = sequenceOf(
            sequenceOf("Player 1:", "43", "19"),
            sequenceOf("Player 2:", "2", "29", "14")
        )

        CombatGame(l).playGame(input, true)

        // No assertion: the game just has to finish in finite time :-)
    }
}
