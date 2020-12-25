package playground.advent2020.day25

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class ComboBreakerTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should find encryption key`() {
        val encryptionKey = ComboBreaker(l).findEncryptionKey(5764801, 17807724)
        assertEquals(14897079, encryptionKey)
    }
}
