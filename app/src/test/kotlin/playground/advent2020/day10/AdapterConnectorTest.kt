package playground.advent2020.day10

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class AdapterConnectorTest {
    @Test
    fun `should find 1 and 3 jumps`() {
        val example1 = sequenceOf("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4").map(String::toInt)

        val result = AdapterConnector(Logger(LogLevel.DEBUG)).connectAll(example1)

        assertEquals(35, result)
    }

    @Test
    fun `should find 1 and 3 jumps bigger example`() {
        val example2 = sequenceOf("28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24", "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34", "10", "3").map(String::toInt)

        val result = AdapterConnector(Logger(LogLevel.DEBUG)).connectAll(example2)

        assertEquals(220, result)

    }
}
