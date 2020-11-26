package playground.utilities

import kotlin.test.Test
import kotlin.test.assertEquals

class FileReaderTest {
    @Test fun `should read input file`() {
        val reader = FileReader()
        val fileContent = reader.readFile(resource("/input.txt"))
        assertEquals("input", fileContent.trim())
    }

    @Test fun `should read comma-separated values`() {
        val reader = FileReader()
        val values = reader.readCommaSeparatedValues(resource("/comma-separated-input.txt")).toList()
        val expectedValues = listOf("1", "3", "2")
        assertEquals(expectedValues, values)
    }
}
