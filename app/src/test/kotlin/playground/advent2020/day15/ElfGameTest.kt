package playground.advent2020.day15

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ElfGameTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should play the game`() {
        val input = listOf(0, 3, 6)
        val result = ElfGame(l).playGame(input)
        assertEquals(436, result)
    }
}
