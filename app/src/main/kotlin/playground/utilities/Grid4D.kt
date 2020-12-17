package playground.utilities

class Grid4D<T>(val values: MutableList<MutableList<MutableList<MutableList<T>>>>) {
    private val indicesX = values[0][0][0].indices
    private val indicesY = values[0][0].indices
    private val indicesZ = values[0].indices
    private val indicesW = values.indices

    constructor(xSize: Int, ySize: Int, zSize: Int, wSize: Int, f: (Int, Int, Int, Int) -> T) : this(
        MutableList(wSize) { w -> MutableList(zSize) { z -> MutableList(ySize) { y -> MutableList(xSize) { x -> f(x, y, z, w) } } } }
    )

    fun copy(): Grid4D<T> {
        return Grid4D(values)
    }

    fun forEachIndexed(f: (Int, Int, Int, Int) -> Unit) {
        values.forEachIndexed { w, sliceLayers ->
            sliceLayers.forEachIndexed { z, layerRows ->
                layerRows.forEachIndexed { y, row ->
                    row.forEachIndexed { x, _ -> f(x, y, z, w) }
                }
            }
        }
    }

    fun valuesAsSequence(): Sequence<T> {
        return sequence {
            values.forEach { sliceLayers -> sliceLayers.forEach { layerRows -> layerRows.forEach { rowValues -> yieldAll(rowValues) } } }
        }
    }

    fun adjacentsFor(pointX: Int, pointY: Int, pointZ: Int, pointW: Int): Sequence<T> {
        return sequence {
            (pointX - 1..pointX + 1).forEach { x ->
                (pointY - 1..pointY + 1).forEach { y ->
                    (pointZ - 1..pointZ + 1).forEach { z ->
                        (pointW - 1..pointW + 1).forEach { w ->
                            if (validCoordinate(x, y, z, w) && (x != pointX || y != pointY || z != pointZ || w != pointW)) {
                                yield(get(x, y, z, w))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validCoordinate(x: Int, y: Int, z: Int, w: Int): Boolean {
        return x in indicesX && y in indicesY && z in indicesZ && w in indicesW
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
        return values.joinToString("\n\n") { sliceLayers ->
            sliceLayers.joinToString("\n\n") { layerRows ->
                layerRows.joinToString("\n") { rowValues ->
                    rowValues.joinToString("", transform = f)
                }
            }
        }
    }

    fun set(x: Int, y: Int, z: Int, w: Int, value: T) {
        values[w][z][y][x] = value
    }

    fun get(x: Int, y: Int, z: Int, w: Int): T {
        return values[w][z][y][x]
    }
}
