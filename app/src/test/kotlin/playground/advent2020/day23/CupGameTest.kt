package playground.advent2020.day23

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class CupGameTest {

    @Test
    fun `should play 100 rounds of the cup game`() {
        val result = CupGame(Logger(LogLevel.DEBUG)).play100Rounds("389125467")
        assertEquals("67384529", result)
    }


    @Test
    @Ignore("Slow test, only run manually")
    fun `should play the big cup game`() {
        val result = CupGame(Logger(LogLevel.INFO)).playBigGame("389125467")
        assertEquals(149245887792, result)
    }
}
