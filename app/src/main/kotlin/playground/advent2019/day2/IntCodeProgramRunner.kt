package playground.advent2019.day2

import playground.utilities.Logger

class IntCodeProgramRunner(val logger: Logger) {

    constructor() : this(Logger())

    fun runIntCodeProgram(program: List<Int>): List<Int> {
        val registry = program.toMutableList()
        var position = 0
        while (!isHaltOperation(position, registry)) {
            executeOperation(position, registry)
            position += 4
        }
        return registry
    }

    private fun isHaltOperation(position: Int, registry: MutableList<Int>): Boolean {
        val isHaltOperation = registry[position] == 99
        if (isHaltOperation) logger.debug("Encountered halt operation at position $position")
        return isHaltOperation
    }

    private fun executeOperation(position: Int, registry: MutableList<Int>) {
        val opCode = registry[position]
        if (!isExecutableOperation(opCode)) {
            throw IllegalStateException("Encountered non-executable opcode $opCode at position $position")
        }

        val position1 = registry[position + 1]
        val position2 = registry[position + 2]
        val targetPosition = registry[position + 3]

        val value1 = registry[position1]
        val value2 = registry[position2]

        if (opCode == 1) {
            val sum = value1 + value2
            registry[targetPosition] = sum
            logger.debug("Adding positions $position1 and $position2 into position $targetPosition ($value1 + $value2 = $sum)")
        } else if (opCode == 2) {
            val product = value1 * value2
            registry[targetPosition] = product
            logger.debug("Multiplying positions $position1 and $position2 into position $targetPosition ($value1 * $value2 = $product)")
        }
    }

    private fun isExecutableOperation(opCode: Int): Boolean {
        return opCode == 1 || opCode == 2
    }

}
