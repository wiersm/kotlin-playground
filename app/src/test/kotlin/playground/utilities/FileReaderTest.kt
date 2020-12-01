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

    @Test fun `should read multiple lines with comma-separated values`() {
        val reader = FileReader()
        val lines = reader.readLinesWithCommaSeparatedValues(resource("/comma-separated-multi-line-input.txt")).toList()

        assertEquals(2, lines.size, "number of lines")

        val valuesLine1 = lines[0].toList()
        val expectedValuesLine1 = listOf("ABC", "ZYX", "C45")
        assertEquals(expectedValuesLine1, valuesLine1, "values line 1")

        val valuesLine2 = lines[1].toList()
        val expectedValuesLine2 = listOf("4", "9", "2", "5")
        assertEquals(expectedValuesLine2, valuesLine2, "values line 2")
    }
}
