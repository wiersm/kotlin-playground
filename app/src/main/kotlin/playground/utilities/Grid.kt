package playground.utilities

class Grid<T>(input: List<List<T>>) {
    val values = input.map{row -> row.toMutableList()}.toMutableList()
    val indicesX = values[0].indices
    val indicesY = values.indices

    fun forEachCoordinate(f: (x: Int, y: Int) -> Unit) {
        for (y in values.indices) for (x in values[y].indices) f(x, y)
    }

    fun copy(): Grid<T> {
        return Grid(values)
    }

    fun adjacentsFor(x: Int, y: Int): Sequence<T> {
        val adjacentValues = mutableListOf<T>()
        val xMax = values[0].size - 1
        val yMax = values.size - 1
        if (x < xMax) {
            adjacentValues.add(get(x + 1, y))
            if (y < yMax) adjacentValues.add(get(x + 1, y + 1))
        }
        if (y < yMax) {
            adjacentValues.add(get(x, y + 1))
            if (x > 0) adjacentValues.add(get(x - 1, y + 1))
        }
        if (x > 0) {
            adjacentValues.add(get(x - 1, y))
            if (y > 0) adjacentValues.add(get(x - 1, y - 1))
        }
        if (y > 0) {
            adjacentValues.add(get(x, y - 1))
            if (x < xMax) adjacentValues.add(get(x + 1, y - 1))
        }
        return adjacentValues.asSequence()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Grid<*>) return false
        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        return values.joinToString("\n") { chars -> chars.joinToString("") }
    }

    fun set(x: Int, y: Int, value: T) {
        values[y][x] = value
    }

    fun get(x: Int, y: Int): T {
        return values[y][x]
    }
}

fun charGridOf(input: Sequence<String>): Grid<Char> = Grid<Char>(input.map{ line -> line.toCharArray().toList()}.toList())
