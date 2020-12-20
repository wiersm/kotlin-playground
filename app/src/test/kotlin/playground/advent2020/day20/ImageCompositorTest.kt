package playground.advent2020.day20

import playground.utilities.FileReader
import playground.utilities.LogLevel
import playground.utilities.Logger
import playground.utilities.resource
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageCompositorTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should multiply corner tiles`() {
        val input = FileReader(l).readBlocksOfLines(resource("/input-advent2020-day20-example.txt"))
        val result = ImageCompositor(l).multiplyCornerTiles(input)
        assertEquals(20899048083289, result)
    }
}
