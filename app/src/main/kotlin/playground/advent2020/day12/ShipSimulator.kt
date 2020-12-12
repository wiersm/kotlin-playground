package playground.advent2020.day12

import com.google.common.math.IntMath
import playground.utilities.Logger
import kotlin.math.abs

class ShipSimulator(val l: Logger) {
    fun determineDistanceTraveled(instructions: Sequence<String>): Int {
        var facing = 'E'
        var x = 0
        var y = 0

        instructions.forEach { instruction ->
            val action = instruction[0]
            val argument = instruction.drop(1).toInt()

            when (action) {
                'R', 'L' -> facing = newDirection(action, argument, facing)
            }

            val movement = when (action) {
                'F' -> facing
                else -> action
            }

            when (movement) {
                'N' -> y += argument
                'E' -> x += argument
                'S' -> y -= argument
                'W' -> x -= argument
            }

            l.debug("New position after $instruction: $x, $y")
        }

        // Return the Manhattan distance traveled.
        return abs(x) + abs(y)
    }

    private fun newDirection(action: Char, argument: Int, currentDirection: Char): Char {
        val directions = listOf('N', 'E', 'S', 'W')
        val currentDirectionIndex = directions.indexOf(currentDirection)
        val turns = argument/90
        val newDirectionIndex = IntMath.mod(
            currentDirectionIndex + if (action == 'R') +turns else -turns,
            directions.size
        )
        val newDirection = directions[newDirectionIndex]
        l.debug("New direction after $action$argument: $newDirection")
        return newDirection
    }
}
