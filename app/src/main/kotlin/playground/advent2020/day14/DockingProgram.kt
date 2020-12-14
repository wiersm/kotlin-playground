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

    fun runInitialisationVersion2(input: Sequence<String>): Long {
        val mem = mutableMapOf<Long, Long>()
        var currentBitmask = AddressBitmask()
        input.forEach { line ->
            when (line.take(3)) {
                "mas" -> {
                    // Update the bitmask
                    currentBitmask = AddressBitmask(line)
                }
                "mem" -> {
                    // Determine the operation
                    val op = MemoryOperation(line)
                    l.debug("Next operation: $op")

                    // Update all memory spaces for the current bitmask.
                    currentBitmask.forEachAddressFor(op.address.toLong()) { address ->
                        l.debug("Setting address $address")
                        mem[address] = op.value
                    }
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

    // Default constructor producing Bitmask that modifies nothing (35 Xs)
    constructor() : this("mask = " + "X".repeat(35))

}

private class AddressBitmask(inputString: String) {
    // Replace some characters so that we can more easily reuse the other Bitmask class:
    // - replace Xs with Ys to indicate that these are the 'floating bits'
    // - replace 0s with Xs because these are positions that are left unchanged.
    val baseBitmaskString = inputString.replace('X', 'Y').replace('0', 'X')

    /** Applies the given function to all addresses derived from the given address for this bitmask. */
    fun forEachAddressFor(address: Long, f: (Long) -> Unit) {
        applyForBitmask(baseBitmaskString, address, f)
    }

    private fun applyForBitmask(bitmaskString: String, address: Long, f: (Long) -> Unit) {
        // If the bitmask no longer contains any floating bits, then we apply it to the address and call the function.
        if (!bitmaskString.contains('Y')) {
            f(Bitmask(bitmaskString).applyTo(address))
        } else {
            // Otherwise we replace the first Y with 0 and 1 and do it again.
            applyForBitmask(bitmaskString.replaceFirst('Y', '0'), address, f)
            applyForBitmask(bitmaskString.replaceFirst('Y', '1'), address, f)
        }
    }

    // Default constructor producing Bitmask that modifies nothing (35 0s)
    constructor() : this("mask = " + "0".repeat(35))
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
