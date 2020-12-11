package playground.utilities

import kotlin.test.Test
import kotlin.test.assertEquals

class GridTest {
    @Test
    fun `test equals`() {
        val grid1 = charGridOf(sequenceOf("XXX", "YYY"))
        val grid2 = charGridOf(sequenceOf("XXX", "YYY"))
        assertEquals(grid1, grid2)
    }
}
