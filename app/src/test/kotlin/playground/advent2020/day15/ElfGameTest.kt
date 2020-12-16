package playground.advent2020.day15

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ElfGameTest {
    val l = Logger(LogLevel.NONE)

    @Test
    fun `should play the game`() {
        val input = listOf(0, 3, 6)
        val result = ElfGame(l).playGame(input, 2020)
        assertEquals(436, result)
    }

    @Test
    @Ignore("Slow test - only run manually")
    fun `should play the long game`() {
        val input = listOf(2, 3, 1)
        val result = ElfGame(Logger(LogLevel.NONE)).playGame(input, 30000000)
        assertEquals(6895259, result)
    }
}
