package playground.advent2020.day7

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class LuggageProcessorTest {
    val input1 = sequenceOf("light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags.")

    @Test
    fun `should figure out suitable containers`() {
        val nrOfSuitableContainers = LuggageProcessor(Logger(LogLevel.NONE)).countSuitableContainers("shiny gold", input1)

        assertEquals(4, nrOfSuitableContainers)
    }

    @Test
    fun `should count contents 1`() {
        val nrRequired = LuggageProcessor(Logger(LogLevel.NONE)).countRequiredContents("shiny gold", input1)
        assertEquals(32, nrRequired)
    }

    @Test
    fun `should count contents 2`() {
        val input2 = sequenceOf("shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags.")
        val nrRequired = LuggageProcessor(Logger(LogLevel.NONE)).countRequiredContents("shiny gold", input2)
        assertEquals(126, nrRequired)
    }
}
