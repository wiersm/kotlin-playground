package playground.advent2020.day2

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidPasswordCounterTest {
    @Test fun `should count the number of valid passwords for sled company`() {
        val input = sequenceOf("1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc")

        val nrOfValidPasswords = ValidPasswordCounter(Logger(LogLevel.DEBUG)).countValidPasswordsForSledCompany(input)

        assertEquals(2, nrOfValidPasswords)
    }

    @Test fun `should count the number of valid passwords for toboggan company`() {
        val input = sequenceOf("1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc")

        val nrOfValidPasswords = ValidPasswordCounter(Logger(LogLevel.DEBUG)).countValidPasswordsForTobogganCompany(input)

        assertEquals(1, nrOfValidPasswords)
    }
}
