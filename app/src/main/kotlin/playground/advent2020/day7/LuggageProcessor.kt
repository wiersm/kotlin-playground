package playground.advent2020.day7

import playground.utilities.Logger
import java.util.function.Function

class LuggageProcessor(val l: Logger) {
    fun countSuitableContainers(requestedColor: String, rules: Sequence<String>): Int {
        val suitableContainersMap = mutableMapOf<String, MutableSet<String>>()
        rules.forEach { rule ->
            val split1 = rule.split(" bags contain ")
            val ruleColor = split1[0]
            l.debug("Rule for color $ruleColor")

            val allowedColors = split1[1].split(", ")
                .map { s -> s.replace(Regex("""\d* (.*) bag.*"""), "$1") }
                .toList()
            l.debug("Contains: $allowedColors")

            // Update the map of suitable containers for the allowed colors.
            allowedColors.forEach {
                suitableContainersMap
                    .computeIfAbsent(it) { mutableSetOf() }
                    .add(ruleColor)
            }
        }

        val suitableContainers = mutableSetOf<String>()
        suitableContainers.addAll(suitableContainersFor(requestedColor, suitableContainersMap))
        l.debug("Full list: $suitableContainers")
        return suitableContainers.size
    }

    private fun suitableContainersFor(requestedColor: String, suitableContainersMap: MutableMap<String, MutableSet<String>>): Collection<String> {
        val suitableContainers = suitableContainersMap[requestedColor] ?: return setOf()
        l.debug("Color $requestedColor can be put in $suitableContainers")

        val allSuitableContainers = suitableContainers.toMutableSet()
        suitableContainers.forEach { allSuitableContainers.addAll(suitableContainersFor(it, suitableContainersMap)) }
        return allSuitableContainers
    }

    fun countRequiredContents(bagColor: String, rules: Sequence<String>): Int {
        val requiredContentsMap = mutableMapOf<String, List<Pair<Int, String>>>()
        rules.forEach { rule ->
            val split1 = rule.split(" bags contain ")
            val ruleColor = split1[0]
            l.debug("Rule for color $ruleColor")

            val requiredContents = split1[1].split(", ")
                .filterNot { s -> s[0] == 'n' }
                .map { s -> s.replace(Regex("""(\d* .*) bag.*"""), "$1") }
                .map { s ->
                    val split = s.split(' ', limit = 2)
                    val amount = split[0].toInt()
                    val color = split[1]
                    Pair(amount, color)
                }
                .toList()
            l.debug("Contains: $requiredContents")

            // Update the map of required contents for each color.
            requiredContentsMap.put(ruleColor, requiredContents)
        }

        return requiredContentsFor(bagColor, requiredContentsMap)
    }

    private fun requiredContentsFor(bagColor: String, requiredContentsMap: Map<String, List<Pair<Int, String>>>): Int {
        return requiredContentsMap[bagColor]!!.map { pair ->
            pair.first + pair.first * requiredContentsFor(pair.second, requiredContentsMap)
        }.sum()
    }

    constructor() : this(Logger())
}
