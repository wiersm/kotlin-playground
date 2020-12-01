package playground.advent2019.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class GridSizeCalculatorTest {
    @Test fun `should calculate grid size for path`() {
        val path = sequenceOf("R8", "U5", "L5", "D3")

        val calculator = GridSizeCalculator()
        calculator.calculateGridSize(path)

        assertEquals(0, calculator.xmin)
        assertEquals(0, calculator.ymin)
        assertEquals(8, calculator.xmax)
        assertEquals(5, calculator.ymax)
    }
}
