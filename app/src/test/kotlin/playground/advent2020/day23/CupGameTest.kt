package playground.advent2020.day23

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class CupGameTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should play 100 rounds of the cup game`() {
        val result = CupGame(l).play100Rounds("389125467")
        assertEquals("67384529", result)
    }
}
