package playground.advent2020.day8

import playground.utilities.Logger

class InstructionParser(val l: Logger) {
    fun parse(input: Sequence<String>): List<Instruction> {
        return input.map { line ->
            val split = line.split(' ')
            val instruction = Instruction(split[0], split[1].toInt())
            l.debug("Parsed: $instruction")
            instruction
        }.toList()
    }
}
