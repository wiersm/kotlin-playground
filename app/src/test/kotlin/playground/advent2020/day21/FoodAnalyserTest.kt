package playground.advent2020.day21

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class FoodAnalyserTest {
    val l = Logger(LogLevel.NONE)

    val input = sequenceOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)"
    )

    @Test
    fun `should count ingredients without allergens`() {
        val result = FoodAnalyser(l).countIngredientsWithoutAllergens(input)

        assertEquals(5, result)
    }

    @Test
    fun `should list ingredients with allergens`() {
        val result = FoodAnalyser(l).listIngredientsWithAllergens(input)

        assertEquals("mxmxvkd,sqjhc,fvjkl", result)
    }
}
