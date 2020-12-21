package playground.advent2020.day21

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class FoodAnalyserTest {
    val l = Logger(LogLevel.DEBUG)

    @Test
    fun `should count ingredients without allergens`() {
        val input = sequenceOf(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
        )

        val result = FoodAnalyser(l).countIngredientsWithoutAllergens(input)

        assertEquals(5, result)
    }
}
