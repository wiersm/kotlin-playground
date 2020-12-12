package playground.advent2020.day12

import com.google.common.math.IntMath.mod
import playground.utilities.Logger
import kotlin.math.abs

enum class Direction(val movement: Pair<Int, Int>) {
    N(Pair(0, 1)),
    E(Pair(1, 0)),
    S(Pair(0, -1)),
    W(Pair(-1, 0));
}

fun directionFor(char: Char): Direction {
    return Direction.valueOf(char.toString())
}

class ShipSimulator(val l: Logger) {
    fun determineDistanceTraveled(instructions: Sequence<String>): Int {
        var facing = Direction.E
        var position = Pair(0, 0)

        instructions.forEach { instruction ->
            val action = instruction[0]
            val argument = instruction.drop(1).toInt()

            when (action) {
                'R', 'L' -> facing = newFacingFor(action, argument, facing)
                'F' -> position += argument * facing.movement
                else -> position += argument * directionFor(action).movement
            }

            l.debug("New position after $instruction: $position")
        }

        // Return the Manhattan distance traveled.
        return position.manhattanDistance
    }

    private fun newFacingFor(action: Char, argument: Int, currentFacing: Direction): Direction {
        val directions = Direction.values()
        val currentDirectionIndex = directions.indexOf(currentFacing)
        val turns = quarterTurnsFor(argument)
        val newDirectionIndex = mod(
            currentDirectionIndex + if (action == 'R') +turns else -turns,
            directions.size
        )
        val newDirection = directions[newDirectionIndex]
        l.debug("New direction after $action$argument: $newDirection")
        return newDirection
    }

    private fun quarterTurnsFor(argument: Int) = argument / 90

    fun determineDistanceTraveledWithWaypoints(instructions: Sequence<String>): Int {
        var shipPosition = Pair(0, 0)
        var waypoint = Pair(10, 1)

        instructions.forEach { instruction ->
            val action = instruction[0]
            val argument = instruction.drop(1).toInt()

            when (action) {
                'F' ->
                    // Move ship in direction of waypoint
                    shipPosition += argument * waypoint
                'R', 'L' ->
                    // Rotate the waypoint
                    waypoint = rotateWayPoint(action, argument, waypoint)
                else ->
                    // Move the waypoint
                    waypoint += argument * directionFor(action).movement
            }

            l.debug("New position after $instruction: $shipPosition, waypoint $waypoint")
        }

        // Return the Manhattan distance traveled
        return shipPosition.manhattanDistance
    }

    private fun rotateWayPoint(action: Char, argument: Int, waypoint: Pair<Int, Int>): Pair<Int, Int> {
        val turns = quarterTurnsFor(argument)
        val clockwiseTurns = mod(if (action == 'R') turns else -turns, 4)
        var newWaypoint = waypoint
        repeat(clockwiseTurns) {
            newWaypoint = Pair(newWaypoint.second, -newWaypoint.first)
        }
        return newWaypoint
    }

}

// Extensions to be able to do arithmetic with Pairs.

/** Create a pair to apply a movement n times. */
private operator fun Int.times(movement: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this * movement.first, this * movement.second)
}

/** Add a movement to a position. */
private operator fun Pair<Int, Int>.plus(movement: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + movement.first, second + movement.second)
}

private val Pair<Int, Int>.manhattanDistance: Int
    get() {
        return abs(first) + abs(second)
    }
