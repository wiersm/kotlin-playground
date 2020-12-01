package playground.advent2019.day3

enum class MoveDirection {
    HORIZONTAL,
    VERTICAL
}

class Move(move: String) {
    val direction: MoveDirection
    val steps: Int

    init {
        val directionCode = move[0]
        val stepSize = move.substring(1).toInt()
        direction = when (directionCode) {
            'R', 'L' -> MoveDirection.HORIZONTAL
            else -> MoveDirection.VERTICAL
        }
        steps = when (directionCode) {
            'R', 'U' -> stepSize
            else -> -stepSize
        }
    }
}
