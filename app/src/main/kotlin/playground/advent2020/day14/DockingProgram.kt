package playground.advent2020.day14

import playground.utilities.Logger

class DockingProgram(val l: Logger) {
    fun runInitialisation(input: Sequence<String>): Long {
        val mem = mutableMapOf<Int, Long>()
        var currentBitmask = Bitmask()
        input.forEach { line ->
            when (line.take(3)) {
                "mas" -> {
                    // Update the bitmask
                    currentBitmask = Bitmask(line)
                }
                "mem" -> {
                    // Update an address space
                    val op = MemoryOperation(line)
                    l.debug("Next operation: $op")
                    val newValue = currentBitmask.applyTo(op.value)
                    l.debug("New value after applying bitmask: $newValue")
                    mem[op.address] = newValue
                }
            }
        }

        // Return the sum of all values in memory
        return mem.values.sum()
    }
}

private class Bitmask(inputString: String) {
    /** Mask for setting bits to zero. */
    val zeroMask: Long
    /** Mask for setting bits to one. */
    val oneMask: Long

    init {
        // Drop the prefix
        val mask = inputString.drop(7)
        // The zero mask consists of only the zeroes with all other bits set to 1
        zeroMask = java.lang.Long.parseLong(mask.replace('X', '1'), 2)
        // The one mask consists of only the ones with all other bits set to 0
        oneMask = java.lang.Long.parseLong(mask.replace('X', '0'), 2)
    }

    fun applyTo(value: Long): Long {
        // Apply the masks bitwise: OR for the one mask to set those bits to one and AND for the zero mask.
        return value or oneMask and zeroMask
    }

    constructor() : this("mask = " + "X".repeat(35))

}

private class MemoryOperation(inputString: String) {
    val address: Int
    val value: Long
    init {
        val split1 = inputString.split(" = ")
        address = split1[0].drop(4).dropLast(1).toInt()
        value = split1[1].toLong()
    }

    override fun toString(): String {
        return "address $address value $value"
    }
}
