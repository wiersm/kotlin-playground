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

    fun multiplyDepartureFields(inputLines: Sequence<String>): Long {
        // Read the input.
        val input = Input(inputLines)

        // First create a list of tickets that have valid fields.
        val validTickets = input.nearbyTickets.filterNot { ticket ->
            ticket.values.any { value ->
                input.rules.none { rule ->
                    rule.validFor(value)
                }
            }
        }

        l.debug("Valid tickets left over: $validTickets")

        // For each field of the ticket, start with a list containing all the rules.
        val nrOfFields = input.myTicket.values.size
        val matchingRulesPerField = List(nrOfFields) { input.rules.toMutableList() }

        // For each of the nearby valid tickets for each field remove rules that do not match.
        validTickets.forEach { ticket ->
            ticket.values.forEachIndexed { index, value ->
                matchingRulesPerField[index].removeIf { rule ->
                    val remove = !rule.validFor(value)
                    if (remove) l.debug("Removing ${rule.fieldName} as an option for field $index")
                    remove
                }
            }
        }

        // Create a map of the fields that have been determined.
        val fieldNames = mutableMapOf<String, Int>()

        // Do the following repeatedly. Should only be necessary at most as many times as there are fields.
        repeat(nrOfFields) {
            // For each field, if there is only one matching rule, then we know the field name.
            matchingRulesPerField.forEachIndexed { index, matchingRules ->
                if (matchingRules.size == 1) {
                    val fieldName = matchingRules[0].fieldName
                    l.debug("Determined that field $index is the $fieldName")
                    fieldNames[fieldName] = index
                }
            }

            // Now remove the known field names as options for the other fields.
            matchingRulesPerField.forEach { rules -> rules.removeIf { rule -> fieldNames.keys.contains(rule.fieldName) } }
        }

        // At this point we should have determined all fields (if the input is correct).
        // Return the product of all the departure fields.
        return fieldNames.filter { entry ->
            // Filter the fields that start with 'departure'
            entry.key.startsWith("departure")
        }.map { entry ->
            // Take the values on my ticket for that field and multiply them all.
            // (The end result will be a big number, so we convert to Long.)
            input.myTicket.values[entry.value].toLong()
        }.reduce(Long::times)
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
    override fun toString(): String {
        return values.toString()
    }
}
