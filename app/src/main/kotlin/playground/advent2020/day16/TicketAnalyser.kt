package playground.advent2020.day16

import playground.utilities.Logger

class TicketAnalyser(val l: Logger) {
    fun sumIncorrectFields(inputLines: Sequence<String>): Int {
        val input = Input(inputLines)
        // Determine the sum of all values that do not match any rule for each of the nearby tickets.
        return input.nearbyTickets.map { ticket ->
            // For each ticket, filter out the values that do not match any rule.
            ticket.values.filterNot { value ->
                input.rules.any { rule ->
                    rule.validFor(value)
                }
            }.sum()
        }.sum()
    }

}

private class Input(inputLines: Sequence<String>) {
    val rules: List<Rule>
    val myTicket: Ticket
    val nearbyTickets: List<Ticket>

    init {
        var readingStage = 0
        val readRules = mutableListOf<Rule>()
        var readMyTicket: Ticket? = null
        val readNearbyTickets = mutableListOf<Ticket>()
        inputLines
            // Remove the header lines that end with a colon
            .filter { it == "" || it.last() != ':' }
            .forEach { line ->
                if (line == "") {
                    readingStage++
                } else {
                    when (readingStage) {
                        0 -> readRules.add(readRule(line))
                        1 -> readMyTicket = readTicket(line)
                        2 -> readNearbyTickets.add(readTicket(line))
                    }
                }
            }
        rules = readRules
        myTicket = readMyTicket!!
        nearbyTickets = readNearbyTickets
    }

    private fun readRule(line: String): Rule {
        // Split to find the field name.
        val fieldNameSplit = line.split(": ")
        // Split the second part to find the ranges.
        val rangeSplit = fieldNameSplit[1].split(" or ").map { it.split('-').map(String::toInt) }
        return Rule(fieldNameSplit[0], rangeSplit[0][0], rangeSplit[0][1], rangeSplit[1][0], rangeSplit[1][1])
    }

    private fun readTicket(line: String): Ticket {
        return Ticket(line.split(',').map(String::toInt))
    }
}

private class Rule(val fieldName: String, val min1: Int, val max1: Int, val min2: Int, val max2: Int) {
    fun validFor(value: Int): Boolean {
        return (value in min1..max1) || (value in min2..max2)
    }

}

private class Ticket(val values: List<Int>) {

}
