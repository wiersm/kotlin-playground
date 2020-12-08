package playground.advent2020.day8

import playground.utilities.Logger

class InstructionRunner(val l: Logger) {
    var infiniteLoopFound = false

    fun run(instructions: List<Instruction>): Int {
        val visited = BooleanArray(instructions.size) { false }
        var position = 0
        var accumulator = 0
        while (position < instructions.size) {
            if (visited[position]) {
                l.debug("Infinite loop detected at position $position, accumulator is $accumulator")
                infiniteLoopFound = true
                break
            }

            visited[position] = true

            val instruction = instructions[position]
            l.debug("Executing instruction at position $position: $instruction")
            when (instruction.operation) {
                "nop" -> position++
                "acc" -> {
                    accumulator += instruction.argument
                    position++
                }
                "jmp" -> position += instruction.argument
            }

            l.debug("Accumulator: $accumulator")
        }
        return accumulator
    }

    fun fixAndRun(instructions: List<Instruction>): Int {
        val changedInstructions = instructions.toMutableList()
        for (position in instructions.indices) {
            val originalInstruction = changedInstructions[position]
            if (originalInstruction.operation == "nop") {
                l.debug("Changing nop to jmp at position $position")
                changedInstructions[position] = Instruction("jmp", originalInstruction.argument)
            } else if (originalInstruction.operation == "jmp") {
                l.debug("Changing jmp to nop at position $position")
                changedInstructions[position] = Instruction("nop", originalInstruction.argument)
            } else {
                l.debug("Skipping changing an acc operation")
                continue
            }

            // Try to run the changed instructions.
            infiniteLoopFound = false
            val accumulator = run(changedInstructions)

            // If an infinite loop was not encountered, then we return the result.
            if (!infiniteLoopFound) {
                l.info("After changing position $position to ${changedInstructions[position].operation}, program ran without an infinite loop with result: $accumulator")
                return accumulator
            }

            // The program was not fixed with this change, so we set back the original instruction and try the next change.
            changedInstructions[position] = originalInstruction
        }


        return -1
    }
}
