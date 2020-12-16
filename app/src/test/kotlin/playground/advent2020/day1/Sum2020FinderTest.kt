package playground.advent2020.day1

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class Sum2020FinderTest {
    @Test fun `should find two numbers adding up to 2020`() {
        val testSet = sequenceOf(1010, 1721, 979, 366, 299, 675, 1456)
        val product = Sum2020Finder(Logger(LogLevel.NONE)).findProduct(testSet)

        assertEquals(514579, product)
    }

    @Test fun `should find three numbers adding up to 2020`() {
        val testSet = sequenceOf(1010, 1721, 979, 366, 299, 675, 1456)
        val product = Sum2020Finder(Logger(LogLevel.NONE)).findTriProduct(testSet)

        assertEquals(241861950, product)
    }
}
