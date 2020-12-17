package playground.utilities

class Grid3D<T>(val values: MutableList<MutableList<MutableList<T>>>) {
    private val indicesX = values[0][0].indices
    private val indicesY = values[0].indices
    private val indicesZ = values.indices

    constructor(xSize: Int, ySize: Int, zSize: Int, f: (Int, Int, Int) -> T) : this(
        MutableList(zSize) { z -> MutableList(ySize) { y -> MutableList(xSize) { x -> f(x, y, z) } } }
    )

    fun copy(): Grid3D<T> {
        return Grid3D(values)
    }

    fun forEachIndexed(f: (Int, Int, Int) -> Unit) {
        values.forEachIndexed { z, layerRows ->
            layerRows.forEachIndexed { y, row ->
                row.forEachIndexed { x, _ -> f(x, y, z) }
            }
        }
    }

    fun valuesAsSequence(): Sequence<T> {
        return sequence {
            values.forEach { it.forEach { yieldAll(it) } }
        }
    }

    fun adjacentsFor(pointX: Int, pointY: Int, pointZ: Int): Sequence<T> {
        return sequence {
            (pointX - 1..pointX + 1).forEach { x ->
                (pointY - 1..pointY + 1).forEach { y ->
                    (pointZ - 1..pointZ + 1).forEach { z ->
                        if (validCoordinate(x, y, z) && (x != pointX || y != pointY || z != pointZ)) yield(get(x, y, z))
                    }
                }
            }
        }
    }

    private fun validCoordinate(x: Int, y: Int, z: Int): Boolean {
        return x in indicesX && y in indicesY && z in indicesZ
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Grid<*>) return false
        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        return toString { t -> t.toString() }
    }

    fun toString(f: ((T) -> CharSequence)?): String {
        return values.joinToString("\n\n") { layerRows ->
            layerRows.joinToString("\n") {
                rowValues -> rowValues.joinToString("", transform = f)
            }
        }
    }

    fun set(x: Int, y: Int, z: Int, value: T) {
        values[z][y][x] = value
    }

    fun get(x: Int, y: Int, z: Int): T {
        return values[z][y][x]
    }
}
