package playground.advent2020.day19

import playground.utilities.Logger

class RuleMatcher(val l: Logger) {
    fun countMatchingMessages(inputLines: Sequence<String>): Int {
        val input = InputParser(l).parse(inputLines)
        return input.messages.filter { message -> Matcher(l).matches(message, input.rules, 0) }.count()
    }
}

private class Matcher(val l: Logger) {
    fun matches(message: String, rules: Map<Int, Rule>, ruleId: Int): Boolean {
        val rule = rules[ruleId] ?: error("Rule $ruleId does not exist")

        val matches = matchesForRule(rule, 0, message, rules).toList()
        l.debug("Matches for message $message: $matches")

        return if (matches.any { match -> match.length == message.length }) {
            l.debug("Message $message has match with right length")
            true
        } else {
            l.debug("Message $message does not match")
            false
        }
    }

    private fun matchesForRule(rule: Rule, startIndex: Int, string: String, rules: Map<Int, Rule>): Sequence<Match> {
        return sequence {
            if (rule.char != null) {
                if (startIndex < string.length && string[startIndex] == rule.char) {
                    l.debug("Character at position $startIndex matches $rule")
                    yield(Match(1))
                }
            } else {
                if (rule.rules1 != null) yieldAll(matchesForCompoundRule(rule.rules1, startIndex, string, rules))
                if (rule.rules2 != null) yieldAll(matchesForCompoundRule(rule.rules2, startIndex, string, rules))
            }
        }
    }

    private fun matchesForCompoundRule(compoundRule: List<Int>, startIndex: Int, string: String, rules: Map<Int, Rule>): Sequence<Match> {
        return sequence {
            // Start with the first rule
            val firstRule = rules[compoundRule[0]] ?: error("Invalid compound rule: $compoundRule")
            var matchesSoFar = matchesForRule(firstRule, startIndex, string, rules)

            // Now try to match the rest of the rules in the compound rule.
            compoundRule.drop(1).forEach { nextRuleId ->
                val nextRule = rules[nextRuleId] ?: error("Invalid compound rule: $compoundRule")

                // Construct a new list of matches matching what we have so far and the next rule.
                // TODO: use a sequence instead of a list here as well
                val newMatches = mutableListOf<Match>()
                matchesSoFar.forEach { previousMatch ->
                    val matchesForNextRule = matchesForRule(nextRule, startIndex + previousMatch.length, string, rules)
                    matchesForNextRule.forEach { nextMatch ->
                        newMatches.add(Match(previousMatch.length + nextMatch.length))
                    }
                }

                // Replace the matches so far with the new list.
                l.debug("New matches for compound rule $compoundRule so far: $newMatches")
                matchesSoFar = newMatches.asSequence()
            }

            // Yield the result.
            yieldAll(matchesSoFar)
        }
    }

    private class Match(val length: Int) {
        override fun toString(): String {
            return "Match (length $length)"
        }
    }
}

private class InputParser(val l: Logger) {
    fun parse(inputLines: Sequence<String>): Input {
        val rules = mutableMapOf<Int, Rule>()
        val messages = mutableListOf<String>()
        var blockIndex = 0
        inputLines.forEach { line ->
            if (line.isBlank()) blockIndex++
            else {
                when (blockIndex) {
                    0 -> {
                        val rule = ruleFor(line)
                        l.debug("Read rule: $rule")
                        rules[rule.id] = rule
                    }
                    1 -> messages.add(line)
                }
            }
        }
        return Input(rules, messages)
    }

    private fun ruleFor(line: String): Rule {
        val splitForId = line.split(": ")
        val id = splitForId[0].toInt()

        if (splitForId[1][0] == '"') {
            return Rule(id, splitForId[1][1])
        } else {
            val ruleIds = splitForId[1].split(" | ").map { side -> side.split(' ').map(String::toInt) }
            return Rule(id, ruleIds[0], ruleIds.getOrNull(1))
        }
    }

}

private class Input (
    val rules: Map<Int, Rule>,
    val messages: List<String>
    )

private class Rule (
    val id: Int,
    val char: Char?,
    val rules1: List<Int>?,
    val rules2: List<Int>?
    ) {
    constructor(id: Int, char: Char) : this(id, char, null, null)
    constructor(id: Int, rules1: List<Int>, rules2: List<Int>?) : this(id, null, rules1, rules2)

    override fun toString(): String {
        return "$id: $char $rules1 $rules2"
    }
}
